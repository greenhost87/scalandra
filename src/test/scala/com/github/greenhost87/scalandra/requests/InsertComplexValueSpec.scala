package com.github.greenhost87.scalandra.requests

import com.datastax.driver.core.Session
import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats.{CassandraFormat, DefaultFormats}
import com.github.greenhost87.scalandra.requests.schema.SchemaUpdater
import com.github.greenhost87.scalandra.requests.utils.{EqualityEnum, Succeed}
import com.github.greenhost87.scalandra.utils.{CassandraScope, EmbeddedCassandra}
import org.joda.time.{DateTime, DateTimeZone}
import org.scalatest.{Matchers, OneInstancePerTest, WordSpec}

class InsertComplexValueSpec
  extends WordSpec
    with Matchers
    with OneInstancePerTest
    with CassandraScope {

  EmbeddedCassandra.start()

  SchemaUpdater.synchronize(Seq(ProfilesTable, MessagesTable), keyspace, cql3Cluster)

  implicit val session: Session = cql3Cluster.connect(keyspace)

  "Insert complex value" should {
    "be able to create Profiles table with complex format mapping" in {

      import Formats._

      val profile = Profile("1234567")

      ProfilesTable
        .insert(profile)
        .execute() shouldBe Succeed

      ProfilesTable
        .select[Profile]
        .where(ProfilesTable.Phone, EqualityEnum.`=`, "1234567")
        .one() shouldBe profile
    }
    "be able to create Messages table with complex format mapping" in {

      import Formats._

      val message = MessageId(ProfileId("1111111"), ProfileId("2222222"), DateTime.now(DateTimeZone.UTC))

      MessagesTable
        .insert(message)
        .execute() shouldBe Succeed
      MessagesTable
        .select[MessageId]
        .where(MessagesTable.ChatId, EqualityEnum.`=`, message.chatId)
        .one() shouldBe message
    }
  }

  "Batch insert" should {
    "work correct execute" in {

      import Formats._

      val profile = Profile("76543210")
      val message = MessageId(ProfileId("1110111"), ProfileId("2220222"), DateTime.now(DateTimeZone.UTC))

      CassandraBatch(
        ProfilesTable.insert(profile).asBatch,
        MessagesTable.insert(message).asBatch
      ).execute() shouldBe Succeed

      ProfilesTable
        .select[Profile]
        .where(ProfilesTable.Phone, EqualityEnum.`=`, "76543210")
        .one() shouldBe profile

      MessagesTable
        .select[MessageId]
        .where(MessagesTable.ChatId, EqualityEnum.`=`, message.chatId)
        .one() shouldBe message
    }
  }

  object Email {
    val maker: (String, Boolean) => Email = { (email: String, verified: Boolean) =>
      Email(email, verified)
    }
  }

  case class Email(value: String, verified: Boolean = false, clean: Boolean = false) {
    val domain: String = value.drop(value.indexOf('@') + 1)

    override def toString: String = value
  }

  object PhoneE164 {
    val maker: (String, Boolean) => PhoneE164 = { (phone: String, verified: Boolean) =>
      PhoneE164(phone, verified)
    }
  }

  case class PhoneE164(digits: String, verified: Boolean = false, clean: Boolean = false)

  object Profile {
    def apply(phone: String): Profile = Profile(
      profileId = ProfileId(phone),
      phone = PhoneE164(phone),
      email = None,
      createdAt = DateTime.now(DateTimeZone.UTC),
      createdFrom = "",
      country = None,
      givenName = None,
      familyName = None,
      birthdate = None
    )
  }

  case class ProfileId(id: String) extends Ordered[ProfileId] {
    override def compare(that: ProfileId): Int = this.id.compareTo(that.id)

    override def toString: String = id
  }

  case class MessageId(requester: ProfileId, peer: ProfileId, timestamp: DateTime) {
    require(timestamp.getZone.equals(DateTimeZone.UTC), s"Expected UTC time zone but found ${timestamp.getZone}: $timestamp")

    def peers: (ProfileId, ProfileId) = (requester, peer)

    val chatId: String = MessageId.chatId(requester, peer)

    override def toString: String = s"$chatId:${timestamp.getMillis}"
  }

  object MessageId {
    val maker: (String, DateTime) => MessageId = { (chatId: String, timestamp: DateTime) =>
      val arr = chatId.split(":").map(ProfileId)
      require(arr.length == 2, s"Expected 2 profile IDs but found ${arr.length}: $chatId")
      MessageId(arr(0), arr(1), timestamp)
    }

    def chatId(profileA: ProfileId, profileB: ProfileId): String = {
      require(profileA != profileB, s"Expected two different profiles but found '$profileA:$profileB'")
      Seq(profileA, profileB).sorted.mkString(":")
    }
  }

  case class Profile(profileId: ProfileId,
                     phone: PhoneE164,
                     email: Option[Email],
                     createdAt: DateTime,
                     createdFrom: String,
                     country: Option[String],
                     givenName: Option[String],
                     familyName: Option[String],
                     birthdate: Option[DateTime])

  object ProfilesTable extends Table {
    override def tableName: String = "profiles"

    val Phone: Column[String] = column
    val IsPhoneVerified: Column[Boolean] = column[Boolean]()
    val Email: Column[String] = column
    val IsEmailVerified: Column[Boolean] = column[Boolean]()
    val CreatedAt: Column[DateTime] = column[DateTime]()
    val CreatedFrom: Column[String] = column
    val Country: Column[String] = column
    val GivenName: Column[String] = column
    val FamilyName: Column[String] = column
    val Birthdate: Column[DateTime] = column[DateTime]()

    override val partitionKeys: Seq[Column[String]] = Seq(Phone)
  }

  object MessagesTable extends Table {
    override def tableName: String = "messages"

    val ChatId = column
    val Timestamp = column[DateTime]()
    override val partitionKeys = Seq(ChatId)
    override val clusteringKeys = Seq(Timestamp)
  }

  object Formats extends DefaultFormats {
    implicit val ProfileIdFormat: CassandraFormat[ProfileId] = format(ProfileId, ProfilesTable.Phone)
    implicit val PhoneE164Format: CassandraFormat[PhoneE164] = format(PhoneE164.maker, ProfilesTable.Phone, ProfilesTable.IsPhoneVerified)
    implicit val EmailFormat: CassandraFormat[Email] = format(Email.maker, ProfilesTable.Email, ProfilesTable.IsEmailVerified)

    implicit val MessageIdFormat: CassandraFormat[MessageId] = format(MessageId.maker,
      MessagesTable.ChatId.read[MessageId](_.chatId),
      MessagesTable.Timestamp.read[MessageId](_.timestamp)
    )

    implicit val ProfileFormat: CassandraFormat[Profile] = format(Profile.apply,
      ProfileIdFormat,
      PhoneE164Format,
      EmailFormat.optional,
      ProfilesTable.CreatedAt,
      ProfilesTable.CreatedFrom,
      ProfilesTable.Country.optional,
      ProfilesTable.GivenName.optional,
      ProfilesTable.FamilyName.optional,
      ProfilesTable.Birthdate.optional
    )
  }

}

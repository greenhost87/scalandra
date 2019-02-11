package com.github.greenhost87.scalandra.requests

import java.util.UUID

import org.joda.time.{DateTime, DateTimeZone}
import org.scalatest.{Matchers, OneInstancePerTest, WordSpec}
import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats.DefaultFormats
import com.github.greenhost87.scalandra.requests.schema.SchemaUpdater
import com.github.greenhost87.scalandra.requests.utils.{EqualityEnum, Succeed}
import com.github.greenhost87.scalandra.utils.{CassandraScope, EmbeddedCassandra}

class ColumnFormatSpec extends WordSpec
  with Matchers with CassandraScope with OneInstancePerTest {

  EmbeddedCassandra.start()

  SchemaUpdater.synchronize(Seq(CFTable), keyspace, cql3Cluster)

  lazy implicit val session = cql3Cluster.connect(keyspace)

  object CFTable extends Table {
    override def tableName: String = "cf_format"

    val ID = column
    val UUIDComlumn = column[UUID]()
    val DateTimeColumn = column[DateTime]()
    val BigDecimalColumn = column[BigDecimal]()
    val LongColumn = column[Long]()
    val IntColumn = column[Int]()
    val StringColumn = column
    val BooleanColumn = column[Boolean]()
    val MapStringStringColumn = column[Map[String, String]]()
    val MapStringIntColumn = column[Map[String, Int]]()
    val MapIntStringColumn = column[Map[Int, String]]()
    val EnumColumn = column[TestEnum.TestValue]()

    override val partitionKeys = Seq(ID)
  }

  import CFTable._

  "UUID format" should {
    "work as expected" in new CassandraScope {

      case class UuidValue(id: String, variable: UUID)

      object Formats extends DefaultFormats {
        implicit val valueFormat = format(UuidValue, ID, UUIDComlumn)
      }

      import Formats._

      val id = "uuid_test"
      val value = UuidValue(id, UUID.randomUUID())

      CFTable
        .insert(value)
        .execute() shouldBe Succeed

      CFTable
        .select[UuidValue]
        .where(ID, EqualityEnum.`=`, id)
        .one() shouldBe value
    }
  }

  "DateTime format" should {
    "work as expected" in new CassandraScope {

      case class DateTimeValue(id: String, variable: DateTime)

      object Formats extends DefaultFormats {
        implicit val valueFormat = format(DateTimeValue, ID, DateTimeColumn)
      }

      SchemaUpdater.synchronize(Seq(CFTable), keyspace, cql3Cluster)

      implicit val session = cql3Cluster.connect(keyspace)

      import Formats._

      val id = "datetime_test"
      val value = DateTimeValue(id, DateTime.now(DateTimeZone.UTC))

      CFTable
        .insert(value)
        .execute() shouldBe Succeed

      CFTable
        .select[DateTimeValue]
        .where(ID, EqualityEnum.`=`, id)
        .one() shouldBe value
    }
  }

  "BigDecimal format" should {
    "work as expected" in new CassandraScope {

      case class BigDecimalValue(id: String, variable: BigDecimal)

      object Formats extends DefaultFormats {
        implicit val valueFormat = format(BigDecimalValue, ID, BigDecimalColumn)
      }

      import Formats._

      val id = "datetime_test"
      val value = BigDecimalValue(id, BigDecimal(10))

      CFTable
        .insert(value)
        .execute() shouldBe Succeed

      CFTable
        .select[BigDecimalValue]
        .where(ID, EqualityEnum.`=`, id)
        .one() shouldBe value
    }
  }

  "Long format" should {
    "work as expected" in new CassandraScope {

      case class LongValue(id: String, variable: Long)

      object Formats extends DefaultFormats {
        implicit val valueFormat = format(LongValue, ID, LongColumn)
      }

      import Formats._

      val id = "long_test"
      val value = LongValue(id, 10L)

      CFTable
        .insert(value)
        .execute() shouldBe Succeed

      CFTable
        .select[LongValue]
        .where(ID, EqualityEnum.`=`, id)
        .one() shouldBe value
    }
  }

  "Int format" should {
    "work as expected" in new CassandraScope {

      case class IntValue(id: String, variable: Int)

      object Formats extends DefaultFormats {
        implicit val valueFormat = format(IntValue, ID, IntColumn)
      }

      import Formats._

      val id = "int_test"
      val value = IntValue(id, 10)

      CFTable
        .insert(value)
        .execute() shouldBe Succeed

      CFTable
        .select[IntValue]
        .where(ID, EqualityEnum.`=`, id)
        .one() shouldBe value
    }
  }

  "String format" should {
    "work as expected" in new CassandraScope {

      case class StringValue(id: String, variable: String)

      object Formats extends DefaultFormats {
        implicit val valueFormat = format(StringValue, ID, StringColumn)
      }

      import Formats._

      val id = "string_test"
      val value = StringValue(id, "test")

      CFTable
        .insert(value)
        .execute() shouldBe Succeed

      CFTable
        .select[StringValue]
        .where(ID, EqualityEnum.`=`, id)
        .one() shouldBe value
    }
  }

  "Boolean format" should {
    "work as expected" in new CassandraScope {

      case class BooleanValue(id: String, variable: Boolean)

      object Formats extends DefaultFormats {
        implicit val valueFormat = format(BooleanValue, ID, BooleanColumn)
      }

      import Formats._

      val id = "boolean_test"
      val value = BooleanValue(id, true)

      CFTable
        .insert(value)
        .execute() shouldBe Succeed

      CFTable
        .select[BooleanValue]
        .where(ID, EqualityEnum.`=`, id)
        .one() shouldBe value
    }
  }

  "Map[String, String] format" should {
    "work as expected" in new CassandraScope {

      case class MapValue(id: String, variable: Map[String, String])

      object Formats extends DefaultFormats {
        implicit val valueFormat = format(MapValue, ID, MapStringStringColumn)
      }

      import Formats._

      val id = "map_string_string_test"
      val value = MapValue(id, Map("test_key" -> "test_value"))

      CFTable
        .insert(value)
        .execute() shouldBe Succeed

      CFTable
        .select[MapValue]
        .where(ID, EqualityEnum.`=`, id)
        .one() shouldBe value
    }
  }

  "Map[String, Int] format" should {
    "work as expected" in new CassandraScope {

      case class MapValue(id: String, variable: Map[String, Int])

      object Formats extends DefaultFormats {
        implicit val valueFormat = format(MapValue, ID, MapStringIntColumn)
      }

      import Formats._

      val id = "map_string_int_test"
      val value = MapValue(id, Map("test_key" -> 42))

      CFTable
        .insert(value)
        .execute() shouldBe Succeed

      CFTable
        .select[MapValue]
        .where(ID, EqualityEnum.`=`, id)
        .one() shouldBe value

    }
  }

  "Map[Int, String] format" should {
    "work as expected" in new CassandraScope {

      case class MapValue(id: String, variable: Map[Int, String])

      object Formats extends DefaultFormats {
        implicit val valueFormat = format(MapValue, ID, MapIntStringColumn)
      }

      import Formats._

      val id = "map_string_int_test"
      val value = MapValue(id, Map(42 -> "test_value"))

      CFTable
        .insert(value)
        .execute() shouldBe Succeed

      CFTable
        .select[MapValue]
        .where(ID, EqualityEnum.`=`, id)
        .one() shouldBe value

    }
  }

  "Enumeration format" should {
    "work as expected" in new CassandraScope {

      import TestEnum._

      case class EnumValue(id: String, variable: TestValue)

      object Formats extends DefaultFormats {
        implicit val TestEnumFormat = enumFormat(TestEnum)

        implicit val valueFormat = format(EnumValue, ID, EnumColumn)
      }

      import Formats._

      val id = "enum_test"
      val value = EnumValue(id, TestEnum.First)

      CFTable
        .insert(value)
        .execute() shouldBe Succeed

      CFTable
        .select[EnumValue]
        .where(ID, EqualityEnum.`=`, id)
        .one() shouldBe value
    }
  }

  object TestEnum extends Enumeration {
    type TestValue = Value

    val First = Value
  }

}

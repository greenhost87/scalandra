package com.github.greenhost87.scalandra.formats

import java.util.UUID

import com.datastax.driver.core.utils.UUIDs
import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.requests.schema.SchemaUpdater
import com.github.greenhost87.scalandra.requests.utils.{EqualityEnum, Succeed}
import com.github.greenhost87.scalandra.utils.{CassandraScope, EmbeddedCassandra}
import org.scalatest.{Matchers, WordSpec}

class CustomFormatSpec extends WordSpec
  with Matchers {

  EmbeddedCassandra.start()

  "custom format" should {
    "correct work when case class without optional fields" in new CassandraScope {

      object Formats extends DefaultFormats {

        import OrdersTable._

        implicit val OrderIdFormat = format(OrderId, Id)
        implicit val OrderTypeFormat = enumFormat(EnumOrderType)
        implicit val MoneyFormat = format(Money, Amount, Ccy)

        implicit val OrderFormat: RootFormat[Order] =
          format(Order,
            OrderIdFormat,
            format(ProfileId, PayerId),
            format(ProfileId, PayeeId),
            Type,
            MoneyFormat,
            Completed
          )
      }

      SchemaUpdater.synchronize(Seq(OrdersTable), keyspace, cql3Cluster)

      implicit val session = cql3Cluster.connect(keyspace)

      import Formats._

      val id = UUIDs.timeBased()
      val order = Order(OrderId(id), ProfileId("1234567"), ProfileId("7654321"), EnumOrderType.PAYMENT, Money(BigDecimal(10), "USD"), completed = false)

      OrdersTable
        .insert(order)
        .execute() shouldBe Succeed

      OrdersTable
        .select[Order]
        .where(OrdersTable.Id, EqualityEnum.`=`, id)
        .one() shouldBe order
    }
    "correct work when case class contains optional fields" in new CassandraScope {

      object Formats extends DefaultFormats {

        import OrdersTable._

        implicit val OrderIdFormat = format(OrderId, Id)
        implicit val OrderTypeFormat = enumFormat(EnumOrderType)
        implicit val MoneyFormat = format(Money, Amount, Ccy)

        val t = format(ProfileId, PayerId)

        implicit val OrderFormat: RootFormat[OrderOption] =
          format(OrderOption,
            OrderIdFormat,
            format(ProfileId, PayerId).optional,
            format(ProfileId, PayeeId).optional,
            Type,
            MoneyFormat,
            Completed
          )
      }

      SchemaUpdater.synchronize(Seq(OrdersTable), keyspace, cql3Cluster)

      implicit val session = cql3Cluster.connect(keyspace)

      import Formats._

      val id = UUIDs.timeBased()
      val order = OrderOption(OrderId(id), Some(ProfileId("1234567")), Some(ProfileId("7654321")), EnumOrderType.PAYMENT, Money(BigDecimal(10), "USD"), completed = false)

      OrdersTable
        .insert(order)
        .execute() shouldBe Succeed

      OrdersTable
        .select[OrderOption]
        .where(OrdersTable.Id, EqualityEnum.`=`, id)
        .one() shouldBe order
    }
    "correct format nested case class" in new CassandraScope {

      case class Tx(id: String, amount: Money, balance: Money)

      SchemaUpdater.synchronize(Seq(TransactionTable), keyspace, cql3Cluster)

      object Formats extends DefaultFormats {

        import TransactionTable._

        implicit val TxFormat = format(Tx,
          Id,
          format(Money, Amount, Ccy),
          format(Money, Balance, Ccy)
        )
      }

      implicit val session = cql3Cluster.connect(keyspace)

      import Formats._

      val entity = Tx("nested_case_class", Money(BigDecimal(1), "USD"), Money(BigDecimal(2), "USD"))

      TransactionTable
        .insert(entity)
        .execute() shouldBe Succeed
      TransactionTable
        .select[Tx]
        .where(TransactionTable.Id, EqualityEnum.`=`, "nested_case_class")
        .one() shouldBe entity
    }
    "correct format nested case class with None in option field" in new CassandraScope {

      case class Tx(id: String, amount: Money, balance: Option[Money])

      SchemaUpdater.synchronize(Seq(TransactionTable), keyspace, cql3Cluster)

      object Formats extends DefaultFormats {

        import TransactionTable._

        implicit val TxFormat = format(Tx,
          Id,
          format(Money, Amount, Ccy),
          format(Money, Balance, Ccy).optional(Balance)
        )
      }

      implicit val session = cql3Cluster.connect(keyspace)

      import Formats._

      val entity = Tx("nested_case_class_none", Money(BigDecimal(1), "USD"), None)

      TransactionTable
        .insert(entity)
        .execute() shouldBe Succeed
      TransactionTable
        .select[Tx]
        .where(TransactionTable.Id, EqualityEnum.`=`, "nested_case_class_none")
        .one() shouldBe entity
    }
    "correct format nested case class with Some in option field" in new CassandraScope {

      case class Tx(id: String, amount: Money, balance: Option[Money])

      SchemaUpdater.synchronize(Seq(TransactionTable), keyspace, cql3Cluster)

      object Formats extends DefaultFormats {

        import TransactionTable._

        implicit val TxFormat = format(Tx,
          Id,
          format(Money, Amount, Ccy),
          format(Money, Balance, Ccy).optional(Balance)
        )
      }

      implicit val session = cql3Cluster.connect(keyspace)

      import Formats._

      val entity = Tx("nested_case_class_some", Money(BigDecimal(1), "USD"), Some(Money(BigDecimal(2), "USD")))

      TransactionTable
        .insert(entity)
        .execute() shouldBe Succeed
      TransactionTable
        .select[Tx]
        .where(TransactionTable.Id, EqualityEnum.`=`, "nested_case_class_some")
        .one() shouldBe entity
    }
  }

  object OrdersTable extends Table {
    override def tableName: String = "orders"

    val Id = column[UUID]()
    val PayerId = column
    val PayeeId = column
    val Type = column[EnumOrderType.OrderType]()
    val Amount = column[BigDecimal]()
    val Ccy = column
    val Completed = column[Boolean]()

    override val partitionKeys = Seq(Id)
  }

  case class OrderId(uuid: UUID)

  case class ProfileId(value: String) {
    override def toString = value
  }

  object EnumOrderType extends Enumeration {
    type OrderType = Value
    val PAYMENT, PAYMENT_REQUEST, WITHDRAWAL = Value
  }

  case class Money(amount: BigDecimal, ccy: String)

  case class Order(id: OrderId, payerId: ProfileId, payeeId: ProfileId, `type`: EnumOrderType.OrderType, amount: Money, completed: Boolean)

  case class OrderOption(id: OrderId, payerId: Option[ProfileId], payeeId: Option[ProfileId], `type`: EnumOrderType.OrderType, amount: Money, completed: Boolean)

  object TransactionTable extends Table {
    override def tableName: String = "txs"

    val Id = column
    val Amount = column[BigDecimal]()
    val Ccy = column
    val Balance = column[BigDecimal]()

    override val partitionKeys = Seq(Id)
  }
}
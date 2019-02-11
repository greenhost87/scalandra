package com.github.greenhost87.scalandra.formats

import org.scalatest.{Matchers, WordSpec}
import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.requests.schema.SchemaUpdater
import com.github.greenhost87.scalandra.requests.utils.Succeed
import com.github.greenhost87.scalandra.utils.{CassandraScope, EmbeddedCassandra}

class OptionFormatSpec
  extends WordSpec
    with Matchers {
  EmbeddedCassandra.start()

  "Option values" should {
    "be read correct" in new CassandraScope {
      SchemaUpdater.synchronize(Seq(TransactionTable), keyspace, cql3Cluster)

      object Formats extends DefaultFormats {

        import TransactionTable._

        implicit val TxFormat = format(Tx,
          Id,
          format(Money, Amount, Ccy),
          format(Money, Balance, Ccy).optional(Balance),
          Description.optional
        )
      }

      implicit val session = cql3Cluster.connect(keyspace)

      import Formats._

      val entity = Tx("test", Money(BigDecimal(1), "USD"), None, None)

      TransactionTable
        .insert(entity)
        .execute() shouldBe Succeed

      TransactionTable
        .select[Tx]
        .all() should contain(entity)
    }
  }

  object TransactionTable extends Table {
    override def tableName: String = "txs"

    val Id = column
    val Amount = column[BigDecimal]()
    val Ccy = column
    val Balance = column[BigDecimal]()
    val Description = column

    override val partitionKeys = Seq(Id)
  }

  case class Money(amount: BigDecimal, ccy: String)

  case class Tx(id: String, amount: Money, balance: Option[Money], description: Option[String])

}
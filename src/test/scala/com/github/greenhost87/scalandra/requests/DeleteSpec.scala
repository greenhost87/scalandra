package com.github.greenhost87.scalandra.requests

import com.datastax.driver.core.Session
import com.github.greenhost87.scalandra.requests.schema.SchemaUpdater
import com.github.greenhost87.scalandra.requests.utils.{EqualityEnum, NotFound, Succeed}
import com.github.greenhost87.scalandra.utils.{CassandraScope, EmbeddedCassandra}
import org.scalatest.{Matchers, OneInstancePerTest, WordSpec}

class DeleteSpec extends WordSpec
  with Matchers
  with OneInstancePerTest
  with CassandraScope
  with TestTable {

  EmbeddedCassandra.start()

  import Formats._

  SchemaUpdater.synchronize(Seq(DataTable), keyspace, cql3Cluster)

  implicit val session: Session = cql3Cluster.connect(keyspace)

  "Delete" should {
    "remove correct column from row" in {

      DataTable
        .insert(FlatData("05", Some("test@test.com"), None))
        .execute()

      DataTable.delete(DataTable.Address)
        .where(DataTable.ID, "05")
        .execute() shouldBe Succeed

      DataTable
        .select[FlatData]
        .where(DataTable.ID, EqualityEnum.`=`, "05")
        .one() shouldBe FlatData("05", None, None)
    }

    "remove correct row by given entity" in {
      val entity = FlatData("06", Some("test@test.com"), None)

      DataTable
        .insert(entity)
        .execute()

      DataTable.delete(entity)
        .execute() shouldBe Succeed

      intercept[NotFound] {
        DataTable
          .select[FlatData]
          .where(DataTable.ID, EqualityEnum.`=`, "06")
          .one()
      }
    }
  }
}
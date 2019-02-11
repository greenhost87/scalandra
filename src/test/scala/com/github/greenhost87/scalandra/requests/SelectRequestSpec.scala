package com.github.greenhost87.scalandra.requests

import com.github.greenhost87.scalandra.requests.schema.SchemaUpdater
import com.github.greenhost87.scalandra.requests.utils.{EqualityEnum, NotFound}
import com.github.greenhost87.scalandra.utils.{CassandraScope, EmbeddedCassandra}
import org.scalatest.{AsyncWordSpec, Matchers, OneInstancePerTest}

class SelectRequestSpec
  extends AsyncWordSpec
    with Matchers
    with CassandraScope
    with OneInstancePerTest
    with TestTable {

  EmbeddedCassandra.start()

  SchemaUpdater.synchronize(Seq(DataTable), keyspace, cql3Cluster)

  implicit val session = cql3Cluster.connect(keyspace)

  import Formats._

  "Select one" should {
    "return NotFound if column empty" in {
      assertThrows[NotFound] {
        DataTable
          .select[String](DataTable.Address)
          .where(DataTable.ID, EqualityEnum.`=`, "11")
          .one()
      }
    }

    "return NotFound if column empty in async request" in {
      DataTable
        .select[String](DataTable.Address)
        .where(DataTable.ID, EqualityEnum.`=`, "11")
        .oneAsync()
        .failed
        .map { result =>
          result shouldBe NotFound
        }
    }

    "return NotFound if row empty" in {
      assertThrows[NotFound] {
        DataTable
          .select[FlatData](DataTable.Address)
          .where(DataTable.ID, EqualityEnum.`=`, "11")
          .one() shouldBe NotFound
      }
    }

    "return NotFound if row empty in async request" in {
      DataTable
        .select[FlatData](DataTable.Address)
        .where(DataTable.ID, EqualityEnum.`=`, "11")
        .oneAsync()
        .failed
        .map { result =>
          result shouldBe NotFound
        }
    }
  }

  "Select all" should {
    "return Empty Seq[T] if column's empty" in {
      DataTable
        .select[String](DataTable.Address)
        .where(DataTable.ID, EqualityEnum.`=`, "11")
        .all() shouldBe Seq.empty[String]
    }
    "return Empty Seq[T] if column's empty in async request" in {
      DataTable
        .select[String](DataTable.Address)
        .where(DataTable.ID, EqualityEnum.`=`, "11")
        .allAsync()
        .map { result =>
          result shouldBe Seq.empty[String]
        }
    }
    "return Empty Seq[T] if rows empty" in {
      DataTable.select[FlatData](DataTable.Address)
        .where(DataTable.ID, EqualityEnum.`=`, "11")
        .all() shouldBe Seq.empty[FlatData]
    }
    "return Empty Seq[T] if rows empty in async request" in {
      DataTable
        .select[FlatData](DataTable.Address)
        .where(DataTable.ID, EqualityEnum.`=`, "11")
        .allAsync()
        .map { result =>
          result shouldBe Seq.empty[FlatData]
        }
    }
  }
}
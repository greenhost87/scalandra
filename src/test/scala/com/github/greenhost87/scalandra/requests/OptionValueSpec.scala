package com.github.greenhost87.scalandra.requests

import com.github.greenhost87.scalandra.requests.schema.SchemaUpdater
import com.github.greenhost87.scalandra.requests.utils.{EqualityEnum, Succeed}
import com.github.greenhost87.scalandra.utils.{CassandraScope, EmbeddedCassandra}
import org.scalatest.{Matchers, OneInstancePerTest, WordSpec}

class OptionValueSpec extends WordSpec
  with Matchers
  with OneInstancePerTest
  with CassandraScope
  with TestTable {

  EmbeddedCassandra.start()

  import Formats._

  SchemaUpdater.synchronize(Seq(DataTable), keyspace, cql3Cluster)

  implicit val session = cql3Cluster.connect(keyspace)

  "Case class with option fields" should {
    "be written to database without errors when values is Some" in {

      val entity = FlatData("10", Some("test@test.com"), Some(false))

      DataTable
        .insert(entity)
        .execute() shouldBe Succeed
    }
    "be written to database without errors when values is None" in {
      val entity = FlatData("20", None, None)

      DataTable
        .insert(entity)
        .execute() shouldBe Succeed
    }
    "be read from database without errors when value is Some" in {
      val data = FlatData("30", Some("test@test.com"), Some(false))
      DataTable
        .insert(data)
        .execute()

      DataTable
        .select[FlatData]
        .where(DataTable.ID, EqualityEnum.`=`, "30")
        .one() shouldBe data
    }
    "be read from database without errors when value is None" in {
      val data = FlatData("40", None, None)

      DataTable
        .insert(data)
        .execute()

      DataTable
        .select[FlatData]
        .where(DataTable.ID, EqualityEnum.`=`, "40")
        .one() shouldBe data
    }
  }

  "Case class with option nested field" should {
    "be written to database without errors when value is Some" in {
      val entity = Data("01", Some(Email("test@test.com", false)))

      DataTable
        .insert(entity)
        .execute() shouldBe Succeed
    }
    "be written to database without errors when value is None" in {
      val entity = Data("02", None)

      DataTable
        .insert(entity)
        .execute() shouldBe Succeed
    }
    "be read from database without errors when value is Some" in {
      val data = Data("03", Some(Email("test@test.com", false)))
      DataTable
        .insert(data)
        .execute()

      DataTable
        .select[Data]
        .where(DataTable.ID, EqualityEnum.`=`, "03")
        .one() shouldBe data
    }
    "be read from database without errors when value is None" in {
      val data = Data("04", None)
      DataTable
        .insert(data)
        .execute()

      DataTable
        .select[Data]
        .where(DataTable.ID, EqualityEnum.`=`, "04")
        .one() shouldBe data
    }
    "be read from database without errors when default false value is Some" in {
      DataTable
        .insert(DataTable.ID, "05", DataTable.Address, "test@test.com")
        .execute()

      DataTable
        .select[Data]
        .where(DataTable.ID, EqualityEnum.`=`, "05")
        .one() shouldBe Data("05", Some(Email("test@test.com", false)))
    }
  }
}
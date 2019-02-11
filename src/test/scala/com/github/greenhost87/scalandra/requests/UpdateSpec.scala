package com.github.greenhost87.scalandra.requests

import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats.{CassandraInt, CassandraString, DefaultFormats, RootFormat}
import com.github.greenhost87.scalandra.requests.utils.EqualityEnum
import org.scalatest.{Matchers, WordSpec}

class UpdateSpec extends WordSpec
  with Matchers {

  case class TestItem(id: Int, name: String)

  "Update requests custom column names" should {
    object TestItemsTable extends Table {
      val ID = column[Int]("item_id")
      val Name = quotedColumn[String]("NAME")

      override def partitionKeys = Seq(ID)

      override def tableName: String = "items"
    }

    object Formats extends DefaultFormats {
      implicit val SingleFormat = format(TestItem, TestItemsTable.ID, TestItemsTable.Name)
    }

    import Formats._

    val entity = TestItem(42, "all you want to know")

    "work as expected" in {
      val request = TestItemsTable.update(entity)
      request.query shouldBe """SET "NAME"=? WHERE item_id=?"""
      request.values shouldBe Seq(CassandraString("all you want to know"), CassandraInt(42))
    }
  }

  "Update requests with case class as source" should {
    object TestItemsTable extends Table {
      val ID: TestItemsTable.Column[Int] = column[Int]()
      val Name: TestItemsTable.Column[String] = column

      override def partitionKeys: Seq[TestItemsTable.Column[Int]] = Seq(ID)

      override def tableName: String = "items"
    }

    object Formats extends DefaultFormats {
      implicit val SingleFormat: RootFormat[TestItem] = format2(TestItemsTable, TestItem)
    }

    import TestItemsTable._
    import Formats._

    val entity = TestItem(42, "all you want to know")

//    "correct accept case class as parameter and correct generate where block based on Table defenition" in {
//      val request = TestItemsTable.update(entity)
//
//      request.query shouldBe "SET name=? WHERE id=?"
//      request.values shouldBe Seq(CassandraString("all you want to know"), CassandraInt(42))
//    }
//
//    "correct add IF EXISTS condition" in {
//      val request = TestItemsTable.update(entity).ifExists
//
//      request.query shouldBe "UPDATE items SET name=? WHERE id=? IF EXISTS"
//    }

    "correct add IF condition" in {
      val rr: TestItemsTable.CassandraUpdateRequest = TestItemsTable.update(entity)
      val request: TestItemsTable.CassandraConditionUpdateRequest = rr.`if`(Name, EqualityEnum.`=`, "test")

      request.query shouldBe "UPDATE items SET name=? WHERE id=? IF name=?"
      request.values shouldBe Seq(CassandraString("all you want to know"), CassandraInt(42), CassandraString("test"))
    }

    "add IF condition after WHERE" in {
      val request = TestItemsTable.update(Name, "new cool name")
        .where(ID, 42)
        .`if`(Name, EqualityEnum.`=`, entity.name)

      request.query shouldBe "UPDATE items SET name=? WHERE id=? IF name=?"
      request.values shouldBe Seq(CassandraString("new cool name"), CassandraInt(42), CassandraString("all you want to know"))
    }

    "correct stack IF condition" in {
      val request = TestItemsTable.update(entity).`if`(Name, EqualityEnum.`=`, "test").and(Name, EqualityEnum.`>`, "not test")

      request.query shouldBe "UPDATE items SET name=? WHERE id=? IF name=? AND name>?"
      request.values shouldBe Seq(CassandraString("all you want to know"), CassandraInt(42), CassandraString("test"), CassandraString("not test"))
    }

    "correct add TTL condition" in {
      val request = TestItemsTable.update(entity).ttl(100)

      request.query shouldBe "UPDATE items USING TTL ? SET name=? WHERE id=?"
      request.values shouldBe Seq(CassandraInt(100), CassandraString("all you want to know"), CassandraInt(42))
    }

    "correct add TTL and IF condition" in {
      val request = TestItemsTable.update(entity).ttl(100).ifEmpty(Name)

      request.query shouldBe "UPDATE items USING TTL ? SET name=? WHERE id=? IF name=null"
      request.values shouldBe Seq(CassandraInt(100), CassandraString("all you want to know"), CassandraInt(42))
    }
  }

  "Update requests with field list as source" should {
    object TestItemsTable extends Table {
      val ID = column[Int]()
      val Name = column

      override def partitionKeys = Seq(ID)

      override def tableName: String = "items"
    }

    object Formats extends DefaultFormats {
      implicit val SingleFormat = format2(TestItemsTable, TestItem)
    }

    import TestItemsTable._
    import Formats._

    "correct generate SET part of request" in {
      val request = TestItemsTable.update(Name, "new name")

      request.query shouldBe "SET name=?"
      request.values shouldBe Seq(CassandraString("new name"))
    }

    "correct add where" in {
      val request = TestItemsTable.update(Name, "new name").where(ID, 10)

      request.query shouldBe "UPDATE items SET name=? WHERE id=?"
      request.values shouldBe Seq(CassandraString("new name"), CassandraInt(10))
    }

    "throw exception if where contains not partition or clustering key" in {
      assertThrows[IllegalArgumentException] {
        TestItemsTable
          .update(Name, "new name")
          .where(Name, "name")
      }
    }

    "correct add and to where" in {
      val request = TestItemsTable.update(Name, "new name").where(ID, 10).and(ID, 10)

      request.query shouldBe "UPDATE items SET name=? WHERE id=? AND id=?"
      request.values shouldBe Seq(CassandraString("new name"), CassandraInt(10), CassandraInt(10))
    }

    "correct accept TTL" in {
      val request = TestItemsTable.update(Name, "new name").ttl(100)

      request.query shouldBe "UPDATE items USING TTL ? SET name=?"
      request.values shouldBe Seq(CassandraString("new name"), CassandraInt(100))
    }

    "correct accept WHERE to request with TTL" in {
      val request = TestItemsTable.update(Name, "new name").ttl(100).where(ID, 10)

      request.query shouldBe "UPDATE items USING TTL ? SET name=? WHERE id=?"
      request.values shouldBe Seq(CassandraString("new name"), CassandraInt(100), CassandraInt(10))
    }
  }
}

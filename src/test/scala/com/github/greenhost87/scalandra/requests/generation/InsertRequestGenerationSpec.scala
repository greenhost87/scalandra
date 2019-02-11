package com.github.greenhost87.scalandra.requests.generation

import org.scalatest.{Matchers, WordSpec}
import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats._

class InsertRequestGenerationSpec 
  extends WordSpec
  with Matchers {

  case class TestItem(id: Int, name: String)

  "Insert requests with custom column names" should {
    object TestItemsTable extends Table {
      val ID = column[Int]("item_id")
      val Name = quotedColumn[String]("NAME")

      override def partitionKeys = Seq(ID)

      override def tableName: String = "items"
    }

    object Formats extends DefaultFormats {
      implicit val TestItemFormat = format(TestItem, TestItemsTable.ID, TestItemsTable.Name)
    }

    import Formats._

    val entity = TestItem(42, "all you want to know")

    "work as expected" in {
      val request = TestItemsTable.insert(entity)

      request.query shouldBe """INSERT INTO items (item_id, "NAME") VALUES (?, ?)"""
      request.values shouldBe Seq(CassandraInt(42), CassandraString("all you want to know"))
    }
  }

  "Insert requests with case class as source" should {
    object TestItemsTable extends Table {
      val ID = column[Int]()
      val Name = column

      override def partitionKeys = Seq(ID)

      override def tableName: String = "items"
    }

    object Formats extends DefaultFormats {
      implicit val TestItemFormat = format2(TestItemsTable, TestItem)
    }

    import Formats._

    "generate correct Item insert" in {
      val request = TestItemsTable.insert(TestItem(42, "all you want to know"))

      request.query shouldBe "INSERT INTO items (id, name) VALUES (?, ?)"
      request.values shouldBe Seq(CassandraInt(42), CassandraString("all you want to know"))
    }

    "correct add IF NOT EXISTS to request" in {
      val request = TestItemsTable.insert(TestItem(42, "all you want to know")).ifNotExists

      request.query shouldBe "INSERT INTO items (id, name) VALUES (?, ?) IF NOT EXISTS"
      request.values shouldBe Seq(CassandraInt(42), CassandraString("all you want to know"))
    }

    "correct add TTL to request with IF NOT EXISTS" in {
      val request = TestItemsTable.insert(TestItem(42, "all you want to know")).ifNotExists.ttl(100)

      request.query shouldBe "INSERT INTO items (id, name) VALUES (?, ?) IF NOT EXISTS USING TTL ?"
      request.values shouldBe Seq(CassandraInt(42), CassandraString("all you want to know"), CassandraInt(100))
    }

    "correct add TTL to request" in {
      val request = TestItemsTable.insert(TestItem(42, "all you want to know")).ttl(100)

      request.query shouldBe "INSERT INTO items (id, name) VALUES (?, ?) USING TTL ?"
      request.values shouldBe Seq(CassandraInt(42), CassandraString("all you want to know"), CassandraInt(100))
    }
  }
}
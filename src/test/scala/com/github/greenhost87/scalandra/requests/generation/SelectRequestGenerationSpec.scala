package com.github.greenhost87.scalandra.requests.generation

import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats._
import com.github.greenhost87.scalandra.requests.utils.{EqualityEnum, OrderEnum}
import org.scalatest.{Matchers, WordSpec}

class SelectRequestGenerationSpec
  extends WordSpec
    with Matchers
    with DefaultFormats {

  case class TestItem(id: Int, name: String)

  implicit object TestItemsTable extends Table {
    val ID = column[Int]
    val Name = column

    override def partitionKeys = Seq(ID)

    override def tableName: String = "items"
  }

  object Formats extends DefaultFormats {
    implicit val TestItemFormat = format2(TestItemsTable, TestItem)
  }

  import Formats._
  import TestItemsTable._

  "Select by entity" should {
    "work as expected in simple case" in {
      val request = TestItemsTable.select[TestItem]

      request.query shouldBe "SELECT id, name FROM items"
    }

    "correct add LIMIT to simple request" in {
      val request = TestItemsTable.select[TestItem].limit(10)

      request.query shouldBe "SELECT id, name FROM items LIMIT ?"
      request.values shouldBe Seq(CassandraInt(10))
    }

    "correct add ORDER BY to simple request" in {
      val request = TestItemsTable.select[TestItem].orderBy(ID, OrderEnum.ASC)

      request.query shouldBe "SELECT id, name FROM items ORDER BY id ASC"
    }

    "correct add where" in {
      val request = TestItemsTable.select[TestItem].where(ID, EqualityEnum.`=`, 10)

      request.query shouldBe "SELECT id, name FROM items WHERE id=?"
      request.values shouldBe Seq(CassandraInt(10))
    }

    "correct add LIMIT with where" in {
      val request = TestItemsTable.select[TestItem].where(ID, EqualityEnum.`=`, 10).limit(15)

      request.query shouldBe "SELECT id, name FROM items WHERE id=? LIMIT ?"
      request.values shouldBe Seq(CassandraInt(10), CassandraInt(15))
    }

    "correct add LIMIT with ORDER BY" in {
      val request = TestItemsTable.select[TestItem].where(ID, EqualityEnum.`=`, 10).orderBy(ID, OrderEnum.ASC).limit(15)

      request.query shouldBe "SELECT id, name FROM items WHERE id=? ORDER BY id ASC LIMIT ?"
      request.values shouldBe Seq(CassandraInt(10), CassandraInt(15))
    }

    "correct add ORDER BY with where" in {
      val request = TestItemsTable.select[TestItem].where(ID, EqualityEnum.`=`, 10).orderBy(ID, OrderEnum.ASC)

      request.query shouldBe "SELECT id, name FROM items WHERE id=? ORDER BY id ASC"
      request.values shouldBe Seq(CassandraInt(10))
    }

    "correct add AND to request with where" in {
      val request = TestItemsTable.select[TestItem].where(ID, EqualityEnum.`=`, 10).and(ID, EqualityEnum.`=`, 11)

      request.query shouldBe "SELECT id, name FROM items WHERE id=? AND id=?"
      request.values shouldBe Seq(CassandraInt(10), CassandraInt(11))
    }

    "correct add LIMIT to to request with composite WHERE" in {
      val request = TestItemsTable.select[TestItem].where(ID, EqualityEnum.`=`, 10).and(ID, EqualityEnum.`=`, 11).limit(20)

      request.query shouldBe "SELECT id, name FROM items WHERE id=? AND id=? LIMIT ?"
      request.values shouldBe Seq(CassandraInt(10), CassandraInt(11), CassandraInt(20))
    }

    "correct add ORDER BY to to request with composite WHERE" in {
      val request = TestItemsTable.select[TestItem].where(ID, EqualityEnum.`=`, 10).and(ID, EqualityEnum.`=`, 11).orderBy(ID, OrderEnum.ASC)

      request.query shouldBe "SELECT id, name FROM items WHERE id=? AND id=? ORDER BY id ASC"
      request.values shouldBe Seq(CassandraInt(10), CassandraInt(11))
    }
  }

  "Select by columns" should {
    "with 1 column" in {
      val request = TestItemsTable.select(ID)

      request.query shouldBe "SELECT id FROM items"
    }
    "with 2 columns" in {
      val request = TestItemsTable.select(ID, Name)

      request.query shouldBe "SELECT id, name FROM items"
    }
  }
}
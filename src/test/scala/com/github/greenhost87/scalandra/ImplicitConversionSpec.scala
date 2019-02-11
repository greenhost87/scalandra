package com.github.greenhost87.scalandra

import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats.{CassandraObject, DefaultFormats}
import org.scalatest.TryValues._
import org.scalatest.{Matchers, WordSpec}

import scala.util.Try

class ImplicitConversionSpec
  extends WordSpec
  with Matchers {

  import com.github.greenhost87.scalandra.formats.PimpedRow._

  case class Single(textColumn: String)

  "everything work as expected" should {
    "converted to CassandraValue in simple case" in {
      object SingleColumnTable extends Table {
        val TextColumn = column

        override def partitionKeys = Seq(TextColumn)

        override def tableName: String = "test"
      }

      object Formats extends DefaultFormats {
        implicit val SingleFormat = format1(SingleColumnTable, Single)
      }

      import Formats._

      Try(Single("test").asCV).success.value shouldBe an[CassandraObject]
    }

    "converted to CassandraValue when name override used" in {
      implicit object SingleColumnTable extends Table {
        val TextColumn = column[String]("test_column_name")

        override def partitionKeys = Seq(TextColumn)

        override def tableName: String = "test"
      }

      object Formats extends DefaultFormats {
        implicit val MineralIdFormat = format1(SingleColumnTable, Single)
      }

      import Formats._

      Try(Single("test").asCV).success.value shouldBe an[CassandraObject]
    }

    "converted to CassandraValue when custom mapping" in {
      implicit object SingleColumnTable extends Table {
        val TextColumn1 = column[String]("random1")
        val TextColumn2 = column[String]("random2")

        override def partitionKeys = Seq(TextColumn1)

        override def tableName: String = "test"
      }

      object Formats extends DefaultFormats {
        implicit val MineralIdFormat = format(Single, SingleColumnTable.TextColumn2)
      }

      import Formats._

      Try(Single("test").asCV).success.value shouldBe an[CassandraObject]
    }

    "converted to CassandraValue and back to case class" in {
      implicit object SingleColumnTable extends Table {
        val TextColumn = column

        override def partitionKeys = Seq(TextColumn)

        override def tableName: String = "test"
      }

      object Formats extends DefaultFormats {
        implicit val SingleFormat = format1(SingleColumnTable, Single)
      }

      import Formats._

      val tryConvert = Try(Single("test").asCV.convertTo[Single])


      tryConvert.success.value shouldBe an[Single]

      tryConvert.success.value.textColumn shouldBe "test"
    }
  }
}
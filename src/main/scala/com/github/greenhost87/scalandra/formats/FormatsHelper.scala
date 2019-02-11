package com.github.greenhost87.scalandra.formats

import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.errors.DeserializationException

trait FormatsHelper {
  this: BasicFormats with CassandraOptionFormat =>

  type CassandraField = (String, CassandraValue)

  import CassandraFormatUtils._

  trait CassandraFormatOrColumn[P] {
    def cf: CassandraFormat[P]
  }

  type CFoC[P] = CassandraFormatOrColumn[P]

  case class ColumnName[P: CassandraFormat](name: String) extends CFoC[P] {
    override val cf: CassandraFormat[P] = format[P]

    def optional: ColumnName[Option[P]] = new ColumnName[Option[P]](name) {
      override val cf: CassandraFormat[Option[P]] = optionFormat[P]
    }

    def read[T](reader: T => P): ColumnWithCustomRead[T, P] = {
      ColumnWithCustomRead(name, reader)
    }
  }

  case class ColumnWithCustomRead[T, P: CassandraFormat](name: String, reader: T => P) extends CFoC[P] {
    override val cf: CassandraFormat[P] = format[P]
  }

  case class ColumnFormatter[P](cf: CassandraFormat[P]) extends CFoC[P] {
    def optional: ColumnFormatter[Option[P]] = {
      ColumnFormatter[Option[P]](OptionFormat[P]()(cf))
    }

    def optional(optionColumn: String): ColumnFormatter[Option[P]] = {
      ColumnFormatter[Option[P]](OptionFormat[P](Seq(optionColumn))(cf))
    }

    def optional(optionColumns: Seq[String]): ColumnFormatter[Option[P]] = {
      ColumnFormatter[Option[P]](OptionFormat[P](optionColumns)(cf))
    }
  }

  implicit def toColumnName[P: CassandraFormat](column: Table#Column[P]): ColumnName[P] = {
    ColumnName[P](column.toString)
  }

  implicit def cassandraFormatToColumnFormatter[P](cf: CassandraFormat[P]): ColumnFormatter[P] = {
    ColumnFormatter[P](cf)
  }

  private[formats] def fromField[P](value: CassandraValue, fieldName: String, formatter: CassandraFormat[P]): P =
    value match {
      case x: CassandraObject if formatter.isInstanceOf[OptionFormat[_]] & !x.fields.contains(fieldName) =>
        None.asInstanceOf[P]
      case x: CassandraObject =>
        try {
          formatter.read(x.fields(fieldName))
        } catch {
          case e: NoSuchElementException =>
            deserializationError("Object is missing required member '" + fieldName + "'", e, fieldName.toString :: Nil)
          case DeserializationException(msg, cause, fieldNames) =>
            deserializationError(msg, cause, fieldName.toString :: fieldNames)
        }
      case _ =>
        deserializationError("Object expected in field '" + fieldName + "'", fieldNames = fieldName.toString :: Nil)
    }

  private[formats] def productElement2Field[T](fieldName: String, value: T, formatter: CassandraFormat[T]): List[CassandraField] = {
    formatter match {
      case _: OptionFormat[_] if value == None =>
        Nil
      case _ =>
        (fieldName, formatter.write(value)) :: Nil
    }
  }

  trait Utils[T <: Product] {
    private[formats] def write[P](columnFormatter: CFoC[P], p: T, idx: Int): List[CassandraField] = {

      columnFormatter match {
        case column: ColumnName[_] =>
          val value = p.productElement(idx).asInstanceOf[P]

          productElement2Field(column.name, value, column.cf)
        case formatter: ColumnFormatter[_] =>
          val value = p.productElement(idx).asInstanceOf[P]

          productElement2Field(idx.toString, value, formatter.cf)
        case column: ColumnWithCustomRead[T, _] =>
          productElement2Field(column.name, column.reader(p).asInstanceOf[P], column.cf)
      }
    }

    private[formats] def toColumnFormatter[P](columnFormatter: CFoC[P], idx: Int): (String, CassandraFormat[P]) =
      columnFormatter match {
        case column: ColumnName[P] =>
          (column.name, column.cf)
        case formatter: ColumnFormatter[P] =>
          (idx.toString, formatter.cf)
        case customReader: ColumnWithCustomRead[_, P] =>
          (customReader.name, customReader.cf)
      }

    private[formats] def read[P](columnFormatter: CFoC[P], value: CassandraValue, idx: Int): P =
      columnFormatter match {
        case column: ColumnName[P] =>
          fromField[P](value, column.name, column.cf)
        case formatter: ColumnFormatter[P] =>
          fromField[P](value, idx.toString, formatter.cf)
        case column: ColumnWithCustomRead[_, P] =>
          fromField[P](value, column.name, column.cf)
      }
  }

}
package com.github.greenhost87.scalandra.formats

import java.util.UUID

import com.datastax.driver.core.{CodecRegistry, Row}
import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats.CassandraFormatUtils._
import org.joda.time.{DateTime, DateTimeZone}

import scala.annotation.implicitNotFound
import scala.reflect.runtime.universe._

@implicitNotFound(msg = "Cannot find CassandraReader or CassandraFormat type class for ${T}")
trait CassandraReader[T] {
  def read(value: CassandraValue): T

  def columnFormatter: Map[String, CassandraFormat[_]] = Map()
}

@implicitNotFound(msg = "Cannot find CassandraWriter or CassandraFormat type class for ${T}")
trait CassandraWriter[T] {
  def write(obj: T): CassandraValue
}

trait RootFormat[T] extends CassandraFormat[T] with RootReader[T] with RootWriter[T] {
  def overrideFormatMapping(column: String, formatter: CassandraFormat[_]): RootFormat[T] = {
    val self = this
    new RootFormat[T] {
      override def write(obj: T): CassandraValue =
        self.write(obj)

      override def read(value: CassandraValue): T =
        self.read(value)

      override def columnFormatter: Map[String, CassandraFormat[_]] =
        self.columnFormatter + (column -> formatter)
    }
  }
}

@implicitNotFound(msg = "Cannot find RootCassandraReader or RootCassandraFormat type class for ${T}")
trait RootReader[T] extends CassandraReader[T]

@implicitNotFound(msg = "Cannot find RootCassandraWriter or RootCassandraFormat type class for ${T}")
trait RootWriter[T] extends CassandraWriter[T]


object PimpedRow {

  class PimpedCassandraAny[T](any: T) {
    def asCV(implicit writer: CassandraWriter[T]): CassandraValue =
      writer.write(any)
  }

  implicit def pimpCassandraValue[T](any: T): PimpedCassandraAny[T] =
    new PimpedCassandraAny(any)

  implicit def pimpRow(row: Row): PimpedRow = {
    new PimpedRow(row)
  }

  class PimpedRow(row: Row) {
    def asCV[T: CassandraFormat](table: Table): CassandraValue = {
      CassandraObject(format[T].columnFormatter.flatMap(mapRowToObject(_, table)).toMap)
    }

    def asCV[T](value: T, f: T => CassandraValue): CassandraValue = {
      Option(value) match {
        case Some(v) =>
          f(v)
        case None =>
          CassandraNull
      }
    }

    private def mapRowToObject(pair: (String, CassandraFormat[_]), table: Table): Map[String, CassandraValue] = {
      if (pair._2.columnFormatter.isEmpty) {
        Map(pair._1 -> asCV(pair._1, table))
      } else {
        val obj = CassandraObject(pair._2.columnFormatter.flatMap(mapRowToObject(_, table)).toMap)

        if (isEmptyOption(pair, obj)) {
          Map(pair._1 -> CassandraNull)
        } else {
          Map(pair._1 -> obj)
        }
      }
    }

    def asCV(columnName: String, table: Table): CassandraValue = {
      val column = table.findByColumnName(columnName)

      asCV(column)
    }

    def asCV(column: Table#Column[_]): CassandraValue = {
//      column.`type` match {
//        case String =>
//
//      }
      if (column.`type` <:< typeOf[String]) {
        extractIfExists[String](column, row.getString(column.toString), CassandraString)
      } else if (column.`type` <:< typeOf[UUID]) {
        extractIfExists[UUID](column, row.getUUID(column.toString), CassandraUUID)
      } else if (column.`type` <:< typeOf[DateTime]) {
        if (row.isNull(column.toString)) {
          CassandraNull
        } else {
          extractIfExists[DateTime](column, new DateTime(row.getTimestamp(column.toString), DateTimeZone.UTC), CassandraDateTime)
        }
      } else if (column.`type` <:< typeOf[BigDecimal]) {
        if (row.isNull(column.toString)) {
          CassandraNull
        } else {
          extractIfExists[BigDecimal](column, BigDecimal(row.getString(column.toString)), CassandraNumber)
        }
      } else if (column.`type` <:< typeOf[Boolean]) {
        if (row.isNull(column.toString)) {
          CassandraDefaultFalse
        } else {
          extractIfExists[Boolean](column, row.getBool(column.toString), CassandraBoolean(_))
        }
      } else if (column.`type` <:< typeOf[Int]) {
        extractIfExists[Int](column, row.getInt(column.toString), CassandraInt)
      } else if (column.`type` <:< typeOf[Map[_, _]]) {
        import scala.collection.JavaConversions._

        row.getColumnDefinitions.asList().find(_.getName == column.toString) match {
          case Some(columnDefinitions) =>
            val keyClass = CodecRegistry.DEFAULT_INSTANCE.codecFor(columnDefinitions.getType.getTypeArguments.get(0)).getJavaType.getRawType
            val valueClass = CodecRegistry.DEFAULT_INSTANCE.codecFor(columnDefinitions.getType.getTypeArguments.get(1)).getJavaType.getRawType

            Option(row.getMap(column.toString, keyClass, valueClass)) match {
              case Some(value) =>
                CassandraMap(value.toMap)
              case None =>
                CassandraNull
            }
          case None =>
            throw new IllegalStateException(s"Can't find column [$column] in row [$row]")
        }
      } else if (column.`type` <:< typeOf[Long]) {
        extractIfExists[Long](column, row.getLong(column.toString), CassandraLong)
      } else if (column.`type` <:< typeOf[Enumeration#Value]) {
        extractIfExists[String](column, row.getString(column.toString), CassandraString)
      } else {
        throw new RuntimeException(s"Can't construct column [$column] from row [$row], column type doesn't acepted")
      }
    }

    private def extractIfExists[T](column: Table#Column[_], fromRow: => T, toResult: T => CassandraValue) =
      Option(fromRow) match {
        case Some(v) =>
          toResult(v)
        case None =>
          CassandraNull
      }

    private def isEmptyOption(pair: (String, CassandraFormat[_]), obj: CassandraObject): Boolean = {
      pair._2 match {
        case o: OptionFormat[_] if obj.isLookLikeNone() =>
          true
        case _ =>
          false
      }
    }
  }

}


trait CassandraFormat[T] extends CassandraReader[T] with CassandraWriter[T]

object CassandraFormatUtils {
  def format[T](implicit cf: CassandraFormat[T]): CassandraFormat[T] = {
    cf
  }
}
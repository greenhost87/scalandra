package com.github.greenhost87.scalandra.formats

import java.util.{Date, UUID}

import com.github.greenhost87.scalandra.formats.CassandraFormatUtils._
import org.joda.time.DateTime

abstract class CassandraValue {
  def convertTo[T: CassandraFormat]: T =
    format[T].read(this)
}

case object CassandraNull extends CassandraValue

case class CassandraObject(fields: Map[String, CassandraValue]) extends CassandraValue {
  def asCassandraObject(errorMsg: String): CassandraObject = {
    this
  }

  def getFields(fieldNames: String*): Seq[CassandraValue] = {
    fieldNames.flatMap(fields.get)(collection.breakOut)
  }

  def isLookLikeNone(optionColumns: Seq[String] = Seq[String]()): Boolean = {
    val filtered = if (optionColumns.isEmpty) {
      fields
    } else {
      fields
        .filter {
          case (column, value) =>
            optionColumns.contains(column)
        }
    }

    filtered
      .values.forall {
      case CassandraDefaultFalse =>
        true
      case CassandraNull =>
        true
      case _ =>
        false
    }
  }

}

case class CassandraString(value: String) extends CassandraValue

case class CassandraUUID(value: UUID) extends CassandraValue

case class CassandraDateTime(value: DateTime) extends CassandraValue

case class CassandraDate(value: Date) extends CassandraValue

case class CassandraInt(value: Int) extends CassandraValue

case class CassandraEnumeration(value: Enumeration#Value) extends CassandraValue

case class CassandraLong(value: Long) extends CassandraValue

case class CassandraNumber(value: BigDecimal) extends CassandraValue

sealed abstract class CassandraBoolean extends CassandraValue {
  def value: Boolean
}

object CassandraBoolean {
  def apply(x: Boolean): CassandraBoolean =
    if (x) {
      CassandraTrue
    } else {
      CassandraFalse
    }

  def unapply(x: CassandraBoolean): Option[Boolean] = Some(x.value)
}

case object CassandraTrue extends CassandraBoolean {
  def value = true
}

case object CassandraFalse extends CassandraBoolean {
  def value = false
}

/**
  * Current cassandra driver return false if column is null.
  * For correct map nested option values we need know when false is false and when false because null.
  */
case object CassandraDefaultFalse extends CassandraBoolean {
  def value = false
}

case class CassandraArray(elements: Vector[CassandraValue]) extends CassandraValue

object CassandraArray {
  val empty = CassandraArray(Vector.empty)

  def apply(elements: CassandraValue*) =
    new CassandraArray(elements.toVector)
}

case class CassandraMap[K, V](map: Map[K, V]) extends CassandraValue
package com.github.greenhost87.scalandra.formats

import java.util.{Date, UUID}

import com.github.greenhost87.scalandra.errors.DeserializationException
import org.joda.time.DateTime

trait BasicFormats {
  protected def deserializationError(msg: String, cause: Throwable = null, fieldNames: List[String] = Nil): Nothing = {
    throw DeserializationException(msg, cause, fieldNames)
  }
  
  implicit object IntCassandraFormat extends CassandraFormat[Int] {
    def write(x: Int) =
      CassandraInt(x)

    def read(value: CassandraValue): Int = value match {
      case CassandraInt(x) =>
        x
      case x =>
        deserializationError("Expected Int as CassandraNumber, but got " + x)
    }
  }

  implicit object UUIDCassandraFormat extends CassandraFormat[UUID] {
    def write(x: UUID) =
      CassandraUUID(x)

    def read(value: CassandraValue): UUID = value match {
      case CassandraUUID(x) =>
        x
      case x =>
        deserializationError("Expected UUID as CassandraUUID, but got " + x)
    }
  }

  implicit object LongCassandraFormat extends CassandraFormat[Long] {
    def write(x: Long) =
      CassandraLong(x)

    def read(value: CassandraValue): Long = value match {
      case CassandraLong(x) =>
        x
      case x =>
        deserializationError("Expected Long as CassandraNumber, but got " + x)
    }
  }

  implicit object FloatCassandraFormat extends CassandraFormat[Float] {
    def write(x: Float): CassandraNumber =
      CassandraNumber(BigDecimal(x.toDouble))

    def read(value: CassandraValue): Float = value match {
      case CassandraNumber(x) =>
        x.floatValue
      case CassandraNull =>
        Float.NaN
      case x =>
        deserializationError("Expected Float as CassandraNumber, but got " + x)
    }
  }

  implicit object DoubleCassandraFormat extends CassandraFormat[Double] {
    def write(x: Double): CassandraNumber =
      CassandraNumber(x)

    def read(value: CassandraValue): Double = value match {
      case CassandraNumber(x) =>
        x.doubleValue
      case CassandraNull =>
        Double.NaN
      case x =>
        deserializationError("Expected Double as CassandraNumber, but got " + x)
    }
  }

  implicit object ByteCassandraFormat extends CassandraFormat[Byte] {
    def write(x: Byte): CassandraNumber =
      CassandraNumber(BigDecimal(x.toDouble))

    def read(value: CassandraValue) = value match {
      case CassandraNumber(x) =>
        x.byteValue
      case x =>
        deserializationError("Expected Byte as CassandraNumber, but got " + x)
    }
  }

  implicit object ShortCassandraFormat extends CassandraFormat[Short] {
    def write(x: Short) =
      CassandraNumber(BigDecimal(x.toDouble))

    def read(value: CassandraValue) = value match {
      case CassandraNumber(x) =>
        x.shortValue
      case x =>
        deserializationError("Expected Short as CassandraNumber, but got " + x)
    }
  }

  implicit object BigDecimalCassandraFormat extends CassandraFormat[BigDecimal] {
    def write(x: BigDecimal) = {
      require(x ne null)
      CassandraNumber(x)
    }

    def read(value: CassandraValue) = value match {
      case CassandraNumber(x) =>
        x
      case x =>
        deserializationError("Expected BigDecimal as CassandraNumber, but got " + x)
    }
  }

  implicit object UnitCassandraFormat extends CassandraFormat[Unit] {
    def write(x: Unit): CassandraNumber = CassandraNumber(1)

    def read(value: CassandraValue): Unit = {

    }
  }

  implicit object BooleanCassandraFormat extends CassandraFormat[Boolean] {
    def write(x: Boolean) = CassandraBoolean(x)

    def read(value: CassandraValue): Boolean = value match {
      case CassandraTrue =>
        true
      case CassandraFalse =>
        false
      case CassandraDefaultFalse =>
        false
      case x =>
        deserializationError("Expected CassandraTrue or CassandraFalse, but got " + x)
    }
  }

  implicit object CharCassandraFormat extends CassandraFormat[Char] {
    def write(x: Char) = CassandraString(String.valueOf(x))

    def read(value: CassandraValue) = value match {
      case CassandraString(x) if x.length == 1 =>
        x.charAt(0)
      case x =>
        deserializationError("Expected Char as single-character CassandraString, but got " + x)
    }
  }

  implicit val StringCassandraFormat  = new CassandraFormat[String] {
    def write(x: String) = {
      require(x ne null)
      CassandraString(x)
    }

    def read(value: CassandraValue) = value match {
      case CassandraString(x) => x
      case x =>
        deserializationError("Expected String as CassandraString, but got " + x)
    }
  }

  implicit object SymbolCassandraFormat extends CassandraFormat[Symbol] {
    def write(x: Symbol) = CassandraString(x.name)

    def read(value: CassandraValue) = value match {
      case CassandraString(x) => Symbol(x)
      case x => deserializationError("Expected Symbol as CassandraString, but got " + x)
    }
  }

  implicit object DateTimeCassandraFormat extends CassandraFormat[DateTime] {
    def write(x: DateTime) =
      CassandraDateTime(x)

    def read(value: CassandraValue) = value match {
      case CassandraDateTime(x) =>
        x
      case x => deserializationError("Expected Symbol as CassandraDateTime, but got " + x)
    }
  }

  implicit object DateCassandraFormat extends CassandraFormat[Date] {
    def write(x: Date) =
      CassandraDate(x)

    def read(value: CassandraValue) = value match {
      case CassandraDate(x) =>
        x
      case x => deserializationError("Expected Symbol as CassandraDate, but got " + x)
    }
  }

  implicit def mapFormat[K: CassandraFormat, V: CassandraFormat] = new CassandraFormat[Map[K, V]] {
    def write(m: Map[K, V]) =
      CassandraMap(m)

    def read(value: CassandraValue) = value match {
      case CassandraMap(x) =>
        x.asInstanceOf[Map[K, V]]
      case x =>
        deserializationError("Expected Map as JsObject, but got " + x)
    }
  }
}
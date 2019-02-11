package com.github.greenhost87.scalandra.formats

trait CassandraOptionFormat {
  this: BasicFormats =>

  implicit def optionFormat[T: CassandraFormat]: CassandraFormat[Option[T]] =
    new OptionFormat[T]
}

protected case class OptionFormat[T](optionColumns: Seq[String] = Seq.empty[String])(implicit cf: CassandraFormat[T]) extends CassandraFormat[Option[T]] {
  def write(option: Option[T]): CassandraValue = option match {
    case Some(x) =>
      cf.write(x)
    case None =>
      CassandraNull
  }

  def read(value: CassandraValue): Option[T] = value match {
    case CassandraNull | CassandraDefaultFalse =>
      None
    case obj: CassandraObject =>
      if (obj.isLookLikeNone(optionColumns)) {
        None
      } else {
        Some(cf.read(obj))
      }
    case x =>
      Some(cf.read(x))
  }

  override def columnFormatter: Map[String, CassandraFormat[_]] = {
    cf.columnFormatter
  }
}
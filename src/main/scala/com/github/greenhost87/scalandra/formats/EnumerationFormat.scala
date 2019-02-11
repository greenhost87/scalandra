package com.github.greenhost87.scalandra.formats


trait EnumerationFormat {
  this: BasicFormats =>
  def enumFormat[T <: Enumeration](enu: T): CassandraFormat[T#Value] = new CassandraFormat[T#Value] {
    def write(obj: T#Value): CassandraString = {
      CassandraString(obj.toString)
    }

    def read(value: CassandraValue): enu.Value = value match {
      case CassandraString(name) =>
        enu.withName(name)
      case something =>
        deserializationError(s"Expected a value from enum $enu instead of $something")
    }
  }
}

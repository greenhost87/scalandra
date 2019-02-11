package com.github.greenhost87.scalandra.formats

trait DefaultFormats
  extends UniversalFormatsN
    with UniversalFormats
    with FormatsHelper
    with CassandraOptionFormat
    with TupleFormats
    with EnumerationFormat
    with BasicFormats

object DefaultFormats extends DefaultFormats
package com.github.greenhost87.scalandra.requests

import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats.CassandraFormat

trait UpdateCollections {
  this: Update with Table =>

  def updateCollection[K: CassandraFormat, V: CassandraFormat](column: Table#Column[Map[K, V]], v1: Tuple2[K, V]): CassandraUpdateRequestWithoutWhere =
    CassandraUpdateRequestWithoutWhere(
      tableName,
      s"SET $column = $column + {'${v1._1}' : '${v1._2}'}"
    )
}

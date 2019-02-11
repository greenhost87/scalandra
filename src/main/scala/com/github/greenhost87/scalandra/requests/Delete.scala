package com.github.greenhost87.scalandra.requests

import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats.{CassandraFormat, CassandraObject, CassandraValue, CassandraWriter}
import com.github.greenhost87.scalandra.requests.executors.ExecuteWriteOrBatchRequest

trait Delete {
  this: Table =>

  import com.github.greenhost87.scalandra.formats.PimpedRow._

  def delete[T](any: T)(implicit writer: CassandraWriter[T]): ExecuteWriteOrBatchRequest = new ExecuteWriteOrBatchRequest {
    lazy val (query: String, values: Seq[CassandraValue]) = {
      writer.write(any) match {
        case CassandraObject(fields) =>
          val keys = partitionKeys ++ clusteringKeys
          val where = keys.map(column => s"$column=?").mkString(" AND ")

          val params = keys.map(_.toString).map { key =>
            fields.find(_._1 == key) match {
              case Some((_, value)) =>
                value
              case None =>
                throw new RuntimeException(s"Can't generate delete row request. Entity must contains value for column [$key]")
            }
          }

          (s"DELETE FROM ${tableName} WHERE $where", params)
        case _ =>
          throw new RuntimeException("This delete can be used ONLY with case class")
      }
    }
  }

  def delete(columns: Table#Column[_]*) =
    CassandraDeleteRequestGenerator(columns)

  case class CassandraDeleteRequestGenerator(columns: Seq[Table#Column[_]]) {
    def where[T: CassandraFormat](column: Table#Column[T], value: T): CassandraDeleteWithWhereRequestGenerator = {
      val columnsString = if (columns.isEmpty) {
        ""
      } else {
        columns.map(_.toString).mkString(", ")
      }
      val query = s"DELETE $columnsString FROM ${tableName} WHERE ${column.toString}=?"

      CassandraDeleteWithWhereRequestGenerator(query, Seq(value.asCV))
    }
  }

  case class CassandraDeleteWithWhereRequestGenerator(query: String, values: Seq[CassandraValue]) extends ExecuteWriteOrBatchRequest {
    def and[T: CassandraFormat](column: Table#Column[T], value: T): CassandraDeleteWithWhereRequestGenerator = {
      CassandraDeleteWithWhereRequestGenerator(s"$query AND ${column.toString}=?", values :+ value.asCV)
    }
  }

}
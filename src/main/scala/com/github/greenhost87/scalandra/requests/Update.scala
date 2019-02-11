package com.github.greenhost87.scalandra.requests

import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats._
import com.github.greenhost87.scalandra.requests.executors.{ExecuteWriteOrBatchRequest, WriteRequestParameters}
import com.github.greenhost87.scalandra.requests.utils.EqualityEnum

trait Update {
  this: Table =>

  import PimpedRow._
  import com.github.greenhost87.scalandra.requests.utils.Const._

  def update[T](any: T)(implicit writer: CassandraWriter[T]): CassandraUpdateRequest = {
    writer.write(any) match {
      case CassandraObject(fields) =>
        val filtered = filterValueFields(fields)

        val parameters = filtered.map { case (column, _) => s"$column=?" }.mkString(delimiter)
        val values = filtered.map(_._2)
        val keyFields = filterKeyFields(fields)
        val where = keyFields.map(_._1).map(column => s"$column=?").mkString(" AND ")
        val keyValues = keyFields.map(_._2)

        CassandraUpdateRequest(s"SET $parameters WHERE $where", values ++ keyValues)
      case _ =>
        throw new RuntimeException("This insert can be used ONLY with case class")
    }
  }

  private def filterValueFields(fields: Map[String, CassandraValue]): Seq[(String, CassandraValue)] = {
    fields.filter {
      case (column, _) if partitionKeys.contains(findByColumnName(column)) || clusteringKeys.contains(findByColumnName(column)) =>
        false
      case (column, CassandraNull) =>
        false
      case _ =>
        true
    }.toSeq.distinct
  }

  private def filterKeyFields(fields: Map[String, CassandraValue]): Seq[(String, CassandraValue)] = {
    fields.filter {
      case (column, _) if partitionKeys.contains(findByColumnName(column)) || clusteringKeys.contains(findByColumnName(column)) =>
        true
      case (_, CassandraNull) =>
        false
      case _ =>
        false
    }.toSeq.distinct
  }

  case class CassandraUpdateWithTTLRequest(query: String, values: Seq[CassandraValue]) extends ExecuteWriteOrBatchRequest with IfMethod

  case class CassandraExecutableUpdateRequest(query: String, values: Seq[CassandraValue]) extends ExecuteWriteOrBatchRequest

  case class CassandraUpdateRequestWithoutWhere(tableName: String, query: String, values: CassandraValue*) {
    def where[C: CassandraFormat](column: Table#Column[C], value: C): CassandraCustomWhereUpdate = {
      if (partitionKeys.contains(column) || clusteringKeys.contains(column)) {
        CassandraCustomWhereUpdate(s"UPDATE $tableName $query WHERE $column=?", values :+ value.asCV)
      } else {
        throw new IllegalArgumentException(s"Table#Column [$column] found in WHERE, but only partition or clustering key can be used in WHERE")
      }
    }

    def ttl(seconds: Int): CassandraUpdateRequestWithoutWhereWithTTL = {
      import DefaultFormats.IntCassandraFormat

      CassandraUpdateRequestWithoutWhereWithTTL(s"UPDATE $tableName USING TTL ? $query", values :+ seconds.asCV)
    }
  }

  case class CassandraUpdateRequestWithoutWhereWithTTL(query: String, values: Seq[CassandraValue]) {
    def where[C: CassandraFormat](column: Table#Column[C], value: C): CassandraCustomWhereUpdate = {
      if (partitionKeys.contains(column) || clusteringKeys.contains(column)) {
        CassandraCustomWhereUpdate(s"$query WHERE $column=?", values :+ value.asCV)
      } else {
        throw new IllegalArgumentException(s"Table#Column [$column] found in WHERE, but only partition or clustering key can be used in WHERE")
      }
    }
  }

  case class CassandraCustomWhereUpdate(query: String, values: Seq[CassandraValue]) extends ExecuteWriteOrBatchRequest with IfMethod {
    def and[C: CassandraFormat](column: Table#Column[C], value: C): CassandraCustomWhereUpdate = {
      CassandraCustomWhereUpdate(s"$query AND $column=?", values :+ value.asCV)
    }
  }

  case class CassandraUpdateRequest(query: String, values: Seq[CassandraValue]) {
    def `if`[C: CassandraFormat](column: Table#Column[C],
                equality: EqualityEnum.Equality,
                value: C): CassandraConditionUpdateRequest = {
      CassandraConditionUpdateRequest(s"UPDATE ${tableName} $query IF $column$equality?", values :+ value.asCV)
    }

    def ttl(seconds: Int): CassandraUpdateWithTTLRequest = {
      import DefaultFormats.IntCassandraFormat

      CassandraUpdateWithTTLRequest(s"UPDATE ${tableName} USING TTL ? $query", Seq(seconds.asCV) ++ values)
    }

    def ifExists: CassandraExecutableUpdateRequest = {
      CassandraExecutableUpdateRequest(s"UPDATE ${tableName} $query IF EXISTS", values)
    }

    def ifEmpty[C: CassandraFormat](column: Table#Column[C]): CassandraConditionUpdateRequest = {
      CassandraConditionUpdateRequest(s"UPDATE ${tableName} $query IF $column=null", values)
    }
  }

  case class CassandraConditionUpdateRequest(query: String, values: Seq[CassandraValue]) extends ExecuteWriteOrBatchRequest {
    def and[C: CassandraFormat](column: Table#Column[C], equality: EqualityEnum.Equality, value: C): CassandraConditionUpdateRequest = {
      CassandraConditionUpdateRequest(s"$query AND $column$equality?", values.toSeq :+ value.asCV)
    }
  }

  private[requests] def asUpdateColumns(columns: Table#Column[_]*): String = {
    columns.map(c => s"$c=?").mkString(delimiter)
  }

  sealed trait IfMethod extends WriteRequestParameters {
    def `if`[C: CassandraFormat](column: Table#Column[C], equality: EqualityEnum.Equality, value: C): CassandraConditionUpdateRequest = {
      CassandraConditionUpdateRequest(s"$query IF $column$equality?", values :+ value.asCV)
    }

    def ifEmpty[C: CassandraFormat](column: Table#Column[C]): CassandraConditionUpdateRequest = {
      CassandraConditionUpdateRequest(s"$query IF $column=null", values)
    }

    def ifExists: CassandraExecutableUpdateRequest = {
      CassandraExecutableUpdateRequest(s"$query IF EXISTS", values)
    }
  }

}
package com.github.greenhost87.scalandra.requests

import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats._
import com.github.greenhost87.scalandra.requests.executors.{ExecuteWriteOrBatchRequest, WriteRequestParameters}

trait Insert {
  this: Table =>

  import PimpedRow._
  import com.github.greenhost87.scalandra.requests.utils.Const._

  private def collapse(cv: (String, CassandraValue)): Map[String, CassandraValue] = {
    cv._2 match {
      case CassandraObject(fields) =>
        fields.flatMap(collapse).toMap
      case _ =>
        Map(cv)
    }
  }

  def insert[T: CassandraFormat](any: T): CassandraInsertRequest = {
    CassandraFormatUtils.format[T].write(any) match {
      case CassandraObject(fields) =>
        val filtered = fields.flatMap(collapse).filter {
          case (_, CassandraNull) =>
            false
          case _ =>
            true
        }.toSeq.distinct

        val names = filtered.map(_._1).mkString(delimiter)
        val parametersPlaceholder = filtered.map(_ => "?").mkString(delimiter)
        val values = filtered.map(_._2)

        CassandraInsertRequest(s"INSERT INTO $tableName ($names) VALUES ($parametersPlaceholder)", values: _*)
      case _ =>
        throw new RuntimeException("This insert can be used ONLY with case class")
    }
  }

  case class CassandraInsertRequest(query: String, values: CassandraValue*) extends ExecuteWriteOrBatchRequest with TtlMethod {
    def ifNotExists: CassandraTransactionalInsertRequest = {
      CassandraTransactionalInsertRequest(s"$query $IF_NOT_EXISTS", values)
    }
  }

  case class CassandraTransactionalInsertRequest(query: String, values: Seq[CassandraValue]) extends ExecuteWriteOrBatchRequest with TtlMethod

  case class CassandraExecutableInsert(query: String, values: Seq[CassandraValue]) extends ExecuteWriteOrBatchRequest

  sealed trait TtlMethod extends BasicFormats with WriteRequestParameters {
    def ttl(seconds: Int): CassandraExecutableInsert = {

      CassandraExecutableInsert(s"$query USING TTL ?", values :+ seconds.asCV)
    }
  }

}
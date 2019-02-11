package com.github.greenhost87.scalandra.requests.executors

import com.datastax.driver.core.{ResultSet, ResultSetFuture}
import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats._
import com.github.greenhost87.scalandra.requests.utils.Const
import com.google.common.util.concurrent.{FutureCallback, Futures}

import scala.concurrent.{ExecutionContext, Future, Promise}

trait BaseExecutor {
  def columnsToRequest(columns: Table#Column[_]*): String =
    columns.distinct.map(_.toString).mkString(Const.delimiter)

  def convert(cv: CassandraValue): java.lang.Object = {
    (cv match {
      case CassandraString(value) =>
        value
      case CassandraNumber(value) =>
        value.toString()
      case CassandraBoolean(value) =>
        Boolean.box(value)
      case CassandraInt(value) =>
        value
      case CassandraLong(value) =>
        Long.box(value)
      case CassandraMap(value) =>
        import scala.collection.JavaConversions._

        mapAsJavaMap(value.toSeq.sortBy(_._1.toString).toMap)
      case CassandraDateTime(value) =>
        val calendar = java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone(value.getZone.getID))
        calendar.setTimeInMillis(value.getMillis)
        calendar.getTime
      case CassandraUUID(value) =>
        value
      case other =>
        throw new IllegalArgumentException(s"Unknown field mapping [$other]")
    }).asInstanceOf[Object]
  }

  implicit class AsyncResultSetImplicits(rsf: ResultSetFuture)(implicit ec: ExecutionContext) {
    def asFuture: Future[ResultSet] = {
      val rsp = Promise[ResultSet]()

      Futures.addCallback(
        rsf,
        new FutureCallback[ResultSet] {
          def onFailure(t: Throwable): Unit =
            rsp failure t

          def onSuccess(result: ResultSet): Unit =
            rsp success result
        }
      )

      rsp.future
    }
  }
}
package com.github.greenhost87.scalandra.requests.executors

import com.datastax.driver.core.{ConsistencyLevel, ResultSet, Session}
import com.github.greenhost87.scalandra.formats.CassandraValue
import com.github.greenhost87.scalandra.requests.utils.{CassandraProblem, Succeed, WriteError, WriteFailed}
import com.github.greenhost87.scalandra.requests.{CassandraBatchItem, PreparedStatementCache}
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.control.NonFatal

trait WriteRequestParameters {
  def query: String

  def values: Seq[CassandraValue]
}

trait WriteExecutor extends BaseExecutor with WriteRequestParameters {
  def convertedValues: Seq[Object]

  def log: Logger

  @throws[WriteError]
  def execute(consistencyLevel: ConsistencyLevel = ConsistencyLevel.QUORUM)(implicit session: Session): Succeed = {
    log.trace(s"Try execute [$query] with parameters [$convertedValues]")

    try {
      val result = session.execute(
        PreparedStatementCache
          .prepare(query, consistencyLevel)
          .bind(convertedValues: _*)
      )

      isSuccess(result)
    } catch {
      case NonFatal(ex) =>
        log.error(s"Exception [${ex.getMessage}] while executing [$query] with parameters [$convertedValues]", ex)
        throw CassandraProblem(ex.getMessage)
    }
  }

  def executeAsync(consistencyLevel: ConsistencyLevel = ConsistencyLevel.QUORUM)
                  (implicit ec: ExecutionContext, session: Session): Future[Succeed] = {
    log.trace(s"Try execute [$query] with parameters [$convertedValues]")

    val result = session.executeAsync(
      PreparedStatementCache
        .prepare(query, consistencyLevel)
        .bind(convertedValues: _*)
    )

    result
      .asFuture
      .map(isSuccess)
      .recover {
        case NonFatal(ex) =>
          log.error(s"Exception [${ex.getMessage}] while executing [$query] with parameters [$convertedValues]", ex)
          throw CassandraProblem(ex.getMessage)
      }
  }

  private def isSuccess(rs: ResultSet): Succeed = {
    if (rs.wasApplied()) {
      Succeed
    } else {
      throw WriteFailed
    }
  }
}

trait ExecuteWriteOrBatchRequest extends WriteExecutor {
  val log: Logger = LoggerFactory.getLogger(getClass)

  lazy val convertedValues: Seq[Object] = values.map(convert)

  def asBatch: CassandraBatchItem = {
    CassandraBatchItem(query, convertedValues)
  }
}
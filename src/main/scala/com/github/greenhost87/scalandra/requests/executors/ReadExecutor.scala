package com.github.greenhost87.scalandra.requests.executors

import com.datastax.driver.core.{ConsistencyLevel, Row, Session}
import com.github.greenhost87.scalandra.formats.CassandraValue
import com.github.greenhost87.scalandra.requests.PreparedStatementCache
import com.github.greenhost87.scalandra.requests.utils.{CassandraError, CassandraProblem, NotFound, ReadError}
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

trait ReadRequestParameters[T] {
  def rowMapper: Row => T

  def query: String

  def values: Seq[CassandraValue]
}

trait ReadExecutor[T] extends ReadRequestParameters[T] with BaseExecutor {

  val log: Logger = LoggerFactory.getLogger(getClass)

  import scala.collection.JavaConversions._

  lazy val convertedValues: Seq[Object] = values.map(convert)

  def all(consistencyLevel: ConsistencyLevel = ConsistencyLevel.ONE)
         (implicit session: Session): Seq[T] = {
    Try(execute(consistencyLevel)) match {
      case Success(result) =>
        result
      case Failure(NotFound) =>
        Seq.empty[T]
      case Failure(ex) =>
        throw ex
    }
  }

  @throws[ReadError]
  def one(consistencyLevel: ConsistencyLevel = ConsistencyLevel.ONE)
         (implicit session: Session): T = {
    takeFirst(execute(consistencyLevel))
  }

  @throws[ReadError]
  def allAsync(consistencyLevel: ConsistencyLevel = ConsistencyLevel.ONE)
              (implicit ec: ExecutionContext, session: Session): Future[Seq[T]] = {
    executeAsync(consistencyLevel).recover {
      case NotFound =>
        Seq.empty[T]
    }
  }

  def oneAsync(consistencyLevel: ConsistencyLevel = ConsistencyLevel.ONE)
              (implicit ec: ExecutionContext, session: Session): Future[T] = {
    executeAsync(consistencyLevel)
      .map(takeFirst)
  }

  private def takeFirst(requestResult: Seq[T]): T =
    requestResult match {
      case result if result.isEmpty =>
        throw NotFound
      case result if result.size > 1 =>
        val msg = s"By request [$query] with parameters [$convertedValues] found more than one entity [$result]"

        log.error(msg)

        throw CassandraProblem(msg)
      case result =>
        result.head
    }

  @throws[ReadError]
  private def execute(consistencyLevel: ConsistencyLevel)
                     (implicit session: Session): Seq[T] = {
    try {
      log.trace(s"Try execute [$query] with parameters [$convertedValues]")

      val resultSet = if (values.nonEmpty) {
        val result = session.execute(
          PreparedStatementCache
            .prepare(s"$query ALLOW FILTERING", consistencyLevel)
            .bind(convertedValues: _*)
        )

        result.all()
      } else {
        val result = session.execute(
          PreparedStatementCache
            .prepare(query, consistencyLevel)
            .bind
        )

        result.all()
      }

      mapRows(resultSet)
    } catch {
      case ce: CassandraError =>
        throw ce

      case NonFatal(ex) =>
        log.error(s"Exception [${ex.getMessage}] while executing [$query] with parameters [$convertedValues]", ex)
        throw CassandraProblem(ex.getMessage)
    }
  }

  private def executeAsync(consistencyLevel: ConsistencyLevel)
                          (implicit ec: ExecutionContext, session: Session): Future[Seq[T]] = {
    log.trace(s"Try execute [$query] with parameters [$convertedValues]")

    val resultSet = if (values.nonEmpty) {
      session.executeAsync(
        PreparedStatementCache
          .prepare(s"$query ALLOW FILTERING", consistencyLevel)
          .bind(convertedValues: _*)
      )
    } else {
      session.executeAsync(
        PreparedStatementCache
          .prepare(query, consistencyLevel)
          .bind
      )
    }

    val result = for {
      rs <- resultSet.asFuture
      rows = rs.all()
    } yield mapRows(rows)

    result.recover {
      case ce: CassandraError =>
        throw ce

      case NonFatal(ex) =>
        log.error(s"Exception [${ex.getMessage}] while executing [$query] with parameters [$convertedValues]", ex)
        throw CassandraProblem(ex.getMessage)
    }
  }

  @throws[ReadError]
  private def mapRows(rows: java.util.List[Row]): Seq[T] = {
    if (rows.isEmpty) {
      throw NotFound
    } else {
      if (rows.size == 1) {
        if (isRowEmpty(rows.head)) {
          throw NotFound
        } else {
          rows.map(rowMapper)
        }
      } else {
        rows.map(rowMapper)
      }
    }
  }

  private def isRowEmpty(row: Row): Boolean =
    row.getColumnDefinitions.foldLeft(true) {
      case (prev, column) =>
        if (prev) {
          row.isNull(column.getName)
        } else {
          false
        }
    }
}
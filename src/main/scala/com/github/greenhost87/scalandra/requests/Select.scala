package com.github.greenhost87.scalandra.requests

import com.datastax.driver.core.Row
import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats._
import com.github.greenhost87.scalandra.requests.executors.{ReadExecutor, ReadRequestParameters}
import com.github.greenhost87.scalandra.requests.utils._

trait Select {

  import CassandraFormatUtils._
  import PimpedRow._

  case class CassandraSimpleSelectRequest[T](query: String, rowMapper: Row => T)
    extends ReadExecutor[T]
      with LimitMethod[T] {

    val values: Seq[CassandraValue] = Seq()

    def where[R: CassandraFormat](column: Table#Column[R], equality: EqualityEnum.Equality, value: R): CassandraSelectWithWhere[T] = {
      CassandraSelectWithWhere[T](s"$query WHERE $column$equality?", rowMapper, Seq(value.asCV))
    }

    def orderBy(column: Table#Column[_], order: OrderEnum.Order): CassandraOrderedSelectRequest[T] = {
      CassandraOrderedSelectRequest[T](s"$query ORDER BY $column $order", rowMapper, Seq())
    }
  }

  case class CassandraOrderedSelectRequest[T](query: String, rowMapper: Row => T, values: Seq[CassandraValue])
    extends ReadExecutor[T]
      with LimitMethod[T]

  case class CassandraExecutableSelect[T](query: String, rowMapper: Row => T, values: Seq[CassandraValue])
    extends ReadExecutor[T]

  case class CassandraSelectWithWhere[T](query: String, rowMapper: Row => T, values: Seq[CassandraValue])
    extends ReadExecutor[T]
      with LimitMethod[T] {
    def and[R: CassandraFormat](column: Table#Column[R], equality: EqualityEnum.Equality, value: R): CassandraSelectWithWhere[T] = {
      CassandraSelectWithWhere[T](s"$query AND $column$equality?", rowMapper, values :+ value.asCV)
    }

    def orderBy(column: Table#Column[_], order: OrderEnum.Order): CassandraOrderedSelectRequest[T] = {
      CassandraOrderedSelectRequest[T](s"$query ORDER BY $column $order", rowMapper, values)
    }
  }

  sealed trait LimitMethod[T] extends ReadRequestParameters[T] {
    def limit(count: Int): CassandraExecutableSelect[T] = {
      import com.github.greenhost87.scalandra.formats.DefaultFormats._

      CassandraExecutableSelect[T](s"$query LIMIT ?", rowMapper, values :+ count.asCV)
    }
  }

  private[requests] def allColumns(entity: (String, CassandraFormat[_])): Seq[String] =
    if (entity._2.columnFormatter.isEmpty) {
      Seq(entity._1)
    } else {
      entity._2.columnFormatter.flatMap(allColumns).toSeq
    }

  private[requests] def columnsForType[T: CassandraFormat]: Seq[String] =
    format[T].columnFormatter.flatMap(allColumns).toSeq.distinct


}
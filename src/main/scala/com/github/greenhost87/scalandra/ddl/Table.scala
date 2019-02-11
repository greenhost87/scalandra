package com.github.greenhost87.scalandra.ddl

import java.util.{Date, UUID}

import com.github.greenhost87.scalandra.requests._
import com.github.greenhost87.scalandra.requests.utils.OrderEnum
import org.joda.time.DateTime

trait Table
  extends Enumeration
    with Insert
    with InsertBoilerplate
    with Select
    with SelectBoilerplate
    with Update
    with UpdateCollections
    with UpdateBoilerplate
    with Delete {

  import reflect.runtime.universe._

  case class Column[T: TypeTag](columnName: Option[String], isStatic: Boolean, forceQuote: Boolean) extends super.Val() {
    private final val quote: String =
      "\""

    override def toString: String =
      if (name.exists(_.isUpper) || forceQuote) {
        quotedValue(name)
      } else {
        name
      }

    def name: String =
      columnName match {
        case Some(cn) =>
          cn
        case None =>
          toNameDelimetedViaUnderscore(super.toString())
      }

    def rawName: String =
      super.toString()

    private def quotedValue(value: String): String =
      quote + value + quote

    val `type`: Type = typeOf[T]

    private def scalaTypeToCassandraType(t: Type): Option[String] =
      if (t <:< typeOf[String]) {
        Some("text")
      } else if (t <:< typeOf[UUID]) {
        Some("uuid")
      } else if (t <:< typeOf[DateTime]) {
        Some("timestamp")
      } else if (t <:< typeOf[Date]) {
        Some("timestamp")
      } else if (t <:< typeOf[Boolean]) {
        Some("boolean")
      } else if (t <:< typeOf[Int]) {
        Some("int")
      } else if (t <:< typeOf[Long]) {
        Some("bigint")
      } else if (t <:< typeOf[BigDecimal]) {
        Some("text")
      } else if (t <:< typeOf[Enumeration#Value]) {
        Some("text")
      } else {
        None
      }

    val cassandraTypeName: String =
      scalaTypeToCassandraType(`type`) match {
        case Some(cassandraType) =>
          cassandraType
        case None =>
          if (`type` <:< typeOf[Map[_, _]]) {
            (scalaTypeToCassandraType(`type`.typeArgs.head), scalaTypeToCassandraType(`type`.typeArgs(1))) match {
              case (Some(keyType), Some(valueType)) =>
                s"map<$keyType, $valueType>"
              case _ =>
                throw new IllegalArgumentException(s"Can't obtain cassandra map key and value type for [${`type`}]")
            }
          } else {
            throw new IllegalArgumentException(s"Can't obtain cassandraTypeName for [${`type`}]")
          }
      }
  }

  lazy val columns: Seq[Column[_]] = values.toSeq.map(convert)

  def findByName[T](name: String): Column[T] =
    values
      .find(_.asInstanceOf[Column[_]].rawName.toLowerCase == name.toLowerCase)
      .getOrElse(throw new NoSuchElementException(s"Can't find Column definition with value name [$name]"))
      .asInstanceOf[Column[T]]

  def findByColumnName(columnName: String): Column[_] =
    values
      .find(_.asInstanceOf[Column[_]].toString == columnName)
      .getOrElse(throw new NoSuchElementException(s"Can't find Column definition with column name [$columnName]"))
      .asInstanceOf[Column[_]]

  def tableName: String

  def partitionKeys: Seq[Column[_]]

  def clusteringKeys: Seq[Column[_]] = Seq()

  def clusteringOrderBy: Seq[(Column[_], OrderEnum.Order)] = Seq()

  /*
   *  Helper methods
   */

  protected def column: Column[String] =
    Column[String](None, false, false)

  protected def column[T: TypeTag](): Column[T] =
    Column[T](None, false, false)

  protected def column[T: TypeTag](name: String): Column[T] =
    Column[T](Some(name), false, false)

  protected def staticColumn[T: TypeTag](): Column[T] =
    Column[T](None, true, false)

  protected def quotedColumn: Column[String] =
    Column[String](None, false, true)

  protected def quotedColumn[T: TypeTag](name: String): Column[T] =
    Column[T](Some(name), false, true)

  private val autoNameRegex = "[A-Z\\d]".r

  private def convert(value: Value) =
    value.asInstanceOf[Column[_]]

  private def toNameDelimetedViaUnderscore(name: String) = {
    if (name.exists(_.isLower)) {
      name(0).toLower + autoNameRegex.replaceAllIn(
        name.substring(1, name.length), {
          matched =>
            "_" + matched.group(0).toLowerCase()
        })
    } else {
      name.toLowerCase
    }
  }
}

object Table {
  implicit def columnToStringColumnName(column: Table#Column[_]): String =
    column.toString
}

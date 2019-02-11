package com.github.greenhost87.scalandra.requests

import java.lang.reflect.Modifier

import com.github.greenhost87.scalandra.ddl.Table
import com.github.greenhost87.scalandra.formats.{DefaultFormats, RootFormat, Unmangle}

import scala.reflect.ClassTag
import scala.util.control.NonFatal

trait TestTable {

  case class Email(address: String, isVerified: Boolean)

  case class Data(id: String, email: Option[Email])

  case class FlatData(id: String, address: Option[String], isVerified: Option[Boolean])

  object DataTable extends Table {
    override def tableName: String = "data"

    val ID = column
    val Address = column
    val IsVerified = column[Boolean]("verified")

    override def partitionKeys = Seq(ID)
  }

  object Formats extends DefaultFormats {
    implicit val EmailFormat = format(Email, DataTable.Address, DataTable.IsVerified)
    implicit val DataFormat: RootFormat[Data] = format(Data, DataTable.ID, EmailFormat.optional)
    implicit val FlatDataFormat = format(FlatData, DataTable.ID, DataTable.Address.optional, DataTable.IsVerified.optional)
  }

}


object RichDDL {

  def createTable[T](clazz: Class[T], name: Option[String] = None): Unit = {

    clazz.getDeclaredFields.map { f =>
      f.getType

    }
  }

  private def extractFieldNames[T](classManifest: ClassTag[T]): Array[String] = {
    val clazz = classManifest.runtimeClass
    try {
      // copy methods have the form copy$default$N(), we need to sort them in order, but must account for the fact
      // that lexical sorting of ...8(), ...9(), ...10() is not correct, so we extract N and sort by N.toInt
      val copyDefaultMethods = clazz.getMethods.filter(_.getName.startsWith("copy$default$")).sortBy(
        _.getName.drop("copy$default$".length).takeWhile(_ != '(').toInt)
      val fields = clazz.getDeclaredFields.filterNot { f =>
        import Modifier._
        (f.getModifiers & (TRANSIENT | STATIC | 0x1000 /* SYNTHETIC*/)) > 0
      }
      if (copyDefaultMethods.length != fields.length)
        sys.error("Case class " + clazz.getName + " declares additional fields")
      if (fields.zip(copyDefaultMethods).exists { case (f, m) => f.getType != m.getReturnType })
        sys.error("Cannot determine field order of case class " + clazz.getName)
      fields.map(f => Unmangle.unmangle(f.getName))
    } catch {
      case NonFatal(ex) => throw new RuntimeException("Cannot automatically determine case class field names and order " +
        "for '" + clazz.getName + "', please use the 'jsonFormat' overload with explicit field name specification", ex)
    }
  }
}
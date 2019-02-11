#!/usr/bin/python
# ==========================================================================================
# ====================================== writeFormats ======================================
# ==========================================================================================
def universalFormat(count):
	param1 = []
	param2 = []
	param3 = []
	param4 = []
	param5 = []
	param6 = []

	for i in range(1, count + 1):
		param1.append("P%d" % (i))
		param2.append("p%d: CFoC[P%d]" % (i, i))
		param3.append("fields ++= write[P%d](p%d, p, %d)" % (i, i, i - 1))
		param4.append("val p%dV = read(p%d, value, %d)" % (i, i, i - 1))
		param5.append("p%dV" % (i))
		param6.append("toColumnFormatter(p%d, %d)" % (i, i - 1))
	
	return """def format[%s, T <: Product](construct: (%s) => T,
                               %s): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T) = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(%d * %d)

      %s

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue) = {
      %s

      construct(%s)
    }

    override val columnFormatter: Map[String, CF[_]] = Map(
      %s
    )
  }""" % (", ".join(param1), ", ".join(param1), ", ".join(param2), count, count + 1, "\n".join(param3), "\n".join(param4), ",".join(param5), ",\n".join(param6))

def writeUniversalFormats(a):
	body = ""
	
	for i in range(1, 23):
		body = body + universalFormat(i) + "\n"
	
	return """package com.github.greenhost87.scalandra.formats

import vc.snapswap.cassandra._

trait UniversalFormats extends BasicFormats with CassandraOptionFormat with FormatsHelper {
%s
}""" % (body)

# ==========================================================================================
# ====================================== writeFormats ======================================
# ==========================================================================================
def universalFormatN(count):
	param1 = []
	param2 = []
	param3 = []
	param4 = []
	param5 = []
	param6 = []
	param7 = []
	param8 = []
	param9 = []

	for i in range(1, count + 1):
		param1.append("P%d: CF" % (i))
		param2.append("P%d" % (i))
		param3.append("p%d" % (i))
		param4.append("table.findByName[P%d](p%d)" % (i, i))
	
	return """def format%d[%s, T <: Product : ClassTag](table: Table, construct: (%s) => T): RootFormat[T] = {
    val Array(%s) =
      extractFieldNames(classTag[T])

    format(
      construct,
      %s
    )
  }""" % (count, ", ".join(param1), ", ".join(param2), ", ".join(param3), ",\n".join(param4))

def writeUniversalFormatsN(a):
	body = ""
	
	for i in range(1, 23):
		body = body + universalFormatN(i) + "\n"
	
	return """package com.github.greenhost87.scalandra.formats

import vc.snapswap.cassandra._
import com.github.greenhost87.scalandra.ddl.Table

import scala.reflect._

trait UniversalFormatsN extends UniversalFormats {
%s
}""" % (body)

# ==========================================================================================
# ====================================== write classes =====================================
# ==========================================================================================
f = open('UniversalFormats.scala','w')
f.write("%s" % (writeUniversalFormats("a")) + "\n")
f.close()

f = open('UniversalFormatsN.scala','w')
f.write("%s" % (writeUniversalFormatsN("a")) + "\n")
f.close()

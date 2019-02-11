package com.github.greenhost87.scalandra.formats

import com.github.greenhost87.scalandra.ddl.Table
import Unmangle._
import scala.reflect._

trait UniversalFormatsN  {
  this: BasicFormats with CassandraOptionFormat with UniversalFormats with FormatsHelper =>

  def format1[P1: CassandraFormat, T <: Product : ClassTag](table: Table, construct: P1 => T): RootFormat[T] = {
    val Array(p1) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1)
    )
  }

  def format2[P1: CassandraFormat, P2: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2) => T): RootFormat[T] = {
    val Array(p1, p2) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2)
    )
  }

  def format3[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3) => T): RootFormat[T] = {
    val Array(p1, p2, p3) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3)
    )
  }

  def format4[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4)
    )
  }

  def format5[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5)
    )
  }

  def format6[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6)
    )
  }

  def format7[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7)
    )
  }

  def format8[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, P8: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7, P8) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7, p8) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7),
      table.findByName[P8](p8)
    )
  }

  def format9[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, P8: CassandraFormat, P9: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7, p8, p9) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7),
      table.findByName[P8](p8),
      table.findByName[P9](p9)
    )
  }

  def format10[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, P8: CassandraFormat, P9: CassandraFormat, P10: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7),
      table.findByName[P8](p8),
      table.findByName[P9](p9),
      table.findByName[P10](p10)
    )
  }

  def format11[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, P8: CassandraFormat, P9: CassandraFormat, P10: CassandraFormat, P11: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7),
      table.findByName[P8](p8),
      table.findByName[P9](p9),
      table.findByName[P10](p10),
      table.findByName[P11](p11)
    )
  }

  def format12[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, P8: CassandraFormat, P9: CassandraFormat, P10: CassandraFormat, P11: CassandraFormat, P12: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7),
      table.findByName[P8](p8),
      table.findByName[P9](p9),
      table.findByName[P10](p10),
      table.findByName[P11](p11),
      table.findByName[P12](p12)
    )
  }

  def format13[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, P8: CassandraFormat, P9: CassandraFormat, P10: CassandraFormat, P11: CassandraFormat, P12: CassandraFormat, P13: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7),
      table.findByName[P8](p8),
      table.findByName[P9](p9),
      table.findByName[P10](p10),
      table.findByName[P11](p11),
      table.findByName[P12](p12),
      table.findByName[P13](p13)
    )
  }

  def format14[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, P8: CassandraFormat, P9: CassandraFormat, P10: CassandraFormat, P11: CassandraFormat, P12: CassandraFormat, P13: CassandraFormat, P14: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7),
      table.findByName[P8](p8),
      table.findByName[P9](p9),
      table.findByName[P10](p10),
      table.findByName[P11](p11),
      table.findByName[P12](p12),
      table.findByName[P13](p13),
      table.findByName[P14](p14)
    )
  }

  def format15[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, P8: CassandraFormat, P9: CassandraFormat, P10: CassandraFormat, P11: CassandraFormat, P12: CassandraFormat, P13: CassandraFormat, P14: CassandraFormat, P15: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7),
      table.findByName[P8](p8),
      table.findByName[P9](p9),
      table.findByName[P10](p10),
      table.findByName[P11](p11),
      table.findByName[P12](p12),
      table.findByName[P13](p13),
      table.findByName[P14](p14),
      table.findByName[P15](p15)
    )
  }

  def format16[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, P8: CassandraFormat, P9: CassandraFormat, P10: CassandraFormat, P11: CassandraFormat, P12: CassandraFormat, P13: CassandraFormat, P14: CassandraFormat, P15: CassandraFormat, P16: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7),
      table.findByName[P8](p8),
      table.findByName[P9](p9),
      table.findByName[P10](p10),
      table.findByName[P11](p11),
      table.findByName[P12](p12),
      table.findByName[P13](p13),
      table.findByName[P14](p14),
      table.findByName[P15](p15),
      table.findByName[P16](p16)
    )
  }

  def format17[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, P8: CassandraFormat, P9: CassandraFormat, P10: CassandraFormat, P11: CassandraFormat, P12: CassandraFormat, P13: CassandraFormat, P14: CassandraFormat, P15: CassandraFormat, P16: CassandraFormat, P17: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7),
      table.findByName[P8](p8),
      table.findByName[P9](p9),
      table.findByName[P10](p10),
      table.findByName[P11](p11),
      table.findByName[P12](p12),
      table.findByName[P13](p13),
      table.findByName[P14](p14),
      table.findByName[P15](p15),
      table.findByName[P16](p16),
      table.findByName[P17](p17)
    )
  }

  def format18[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, P8: CassandraFormat, P9: CassandraFormat, P10: CassandraFormat, P11: CassandraFormat, P12: CassandraFormat, P13: CassandraFormat, P14: CassandraFormat, P15: CassandraFormat, P16: CassandraFormat, P17: CassandraFormat, P18: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7),
      table.findByName[P8](p8),
      table.findByName[P9](p9),
      table.findByName[P10](p10),
      table.findByName[P11](p11),
      table.findByName[P12](p12),
      table.findByName[P13](p13),
      table.findByName[P14](p14),
      table.findByName[P15](p15),
      table.findByName[P16](p16),
      table.findByName[P17](p17),
      table.findByName[P18](p18)
    )
  }

  def format19[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, P8: CassandraFormat, P9: CassandraFormat, P10: CassandraFormat, P11: CassandraFormat, P12: CassandraFormat, P13: CassandraFormat, P14: CassandraFormat, P15: CassandraFormat, P16: CassandraFormat, P17: CassandraFormat, P18: CassandraFormat, P19: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7),
      table.findByName[P8](p8),
      table.findByName[P9](p9),
      table.findByName[P10](p10),
      table.findByName[P11](p11),
      table.findByName[P12](p12),
      table.findByName[P13](p13),
      table.findByName[P14](p14),
      table.findByName[P15](p15),
      table.findByName[P16](p16),
      table.findByName[P17](p17),
      table.findByName[P18](p18),
      table.findByName[P19](p19)
    )
  }

  def format20[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, P8: CassandraFormat, P9: CassandraFormat, P10: CassandraFormat, P11: CassandraFormat, P12: CassandraFormat, P13: CassandraFormat, P14: CassandraFormat, P15: CassandraFormat, P16: CassandraFormat, P17: CassandraFormat, P18: CassandraFormat, P19: CassandraFormat, P20: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7),
      table.findByName[P8](p8),
      table.findByName[P9](p9),
      table.findByName[P10](p10),
      table.findByName[P11](p11),
      table.findByName[P12](p12),
      table.findByName[P13](p13),
      table.findByName[P14](p14),
      table.findByName[P15](p15),
      table.findByName[P16](p16),
      table.findByName[P17](p17),
      table.findByName[P18](p18),
      table.findByName[P19](p19),
      table.findByName[P20](p20)
    )
  }

  def format21[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, P8: CassandraFormat, P9: CassandraFormat, P10: CassandraFormat, P11: CassandraFormat, P12: CassandraFormat, P13: CassandraFormat, P14: CassandraFormat, P15: CassandraFormat, P16: CassandraFormat, P17: CassandraFormat, P18: CassandraFormat, P19: CassandraFormat, P20: CassandraFormat, P21: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7),
      table.findByName[P8](p8),
      table.findByName[P9](p9),
      table.findByName[P10](p10),
      table.findByName[P11](p11),
      table.findByName[P12](p12),
      table.findByName[P13](p13),
      table.findByName[P14](p14),
      table.findByName[P15](p15),
      table.findByName[P16](p16),
      table.findByName[P17](p17),
      table.findByName[P18](p18),
      table.findByName[P19](p19),
      table.findByName[P20](p20),
      table.findByName[P21](p21)
    )
  }

  def format22[P1: CassandraFormat, P2: CassandraFormat, P3: CassandraFormat, P4: CassandraFormat, P5: CassandraFormat, P6: CassandraFormat, P7: CassandraFormat, P8: CassandraFormat, P9: CassandraFormat, P10: CassandraFormat, P11: CassandraFormat, P12: CassandraFormat, P13: CassandraFormat, P14: CassandraFormat, P15: CassandraFormat, P16: CassandraFormat, P17: CassandraFormat, P18: CassandraFormat, P19: CassandraFormat, P20: CassandraFormat, P21: CassandraFormat, P22: CassandraFormat, T <: Product : ClassTag](table: Table, construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22) => T): RootFormat[T] = {
    val Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22) =
      extractFieldNames(classTag[T])

    format(
      construct,
      table.findByName[P1](p1),
      table.findByName[P2](p2),
      table.findByName[P3](p3),
      table.findByName[P4](p4),
      table.findByName[P5](p5),
      table.findByName[P6](p6),
      table.findByName[P7](p7),
      table.findByName[P8](p8),
      table.findByName[P9](p9),
      table.findByName[P10](p10),
      table.findByName[P11](p11),
      table.findByName[P12](p12),
      table.findByName[P13](p13),
      table.findByName[P14](p14),
      table.findByName[P15](p15),
      table.findByName[P16](p16),
      table.findByName[P17](p17),
      table.findByName[P18](p18),
      table.findByName[P19](p19),
      table.findByName[P20](p20),
      table.findByName[P21](p21),
      table.findByName[P22](p22)
    )
  }

}

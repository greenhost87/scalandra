package com.github.greenhost87.scalandra.formats

trait UniversalFormats {
  this: BasicFormats with CassandraOptionFormat with FormatsHelper =>
  def format[P1, T <: Product](construct: P1 => T,
                               p1: CFoC[P1]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(1 * 2)

      fields ++= write[P1](p1, p, 0)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)

      construct(p1V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0)
    )
  }

  def format[P1, P2, T <: Product](construct: (P1, P2) => T,
                                   p1: CFoC[P1], p2: CFoC[P2]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(2 * 3)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)

      construct(p1V, p2V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1)
    )
  }

  def format[P1, P2, P3, T <: Product](construct: (P1, P2, P3) => T,
                                       p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(3 * 4)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)

      construct(p1V, p2V, p3V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2)
    )
  }

  def format[P1, P2, P3, P4, T <: Product](construct: (P1, P2, P3, P4) => T,
                                           p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(4 * 5)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)

      construct(p1V, p2V, p3V, p4V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3)
    )
  }

  def format[P1, P2, P3, P4, P5, T <: Product](construct: (P1, P2, P3, P4, P5) => T,
                                               p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(5 * 6)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)

      construct(p1V, p2V, p3V, p4V, p5V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, T <: Product](construct: (P1, P2, P3, P4, P5, P6) => T,
                                                   p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(6 * 7)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)

      construct(p1V, p2V, p3V, p4V, p5V, p6V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7) => T,
                                                       p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(7 * 8)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, P8, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7, P8) => T,
                                                           p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7], p8: CFoC[P8]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(8 * 9)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)
      fields ++= write[P8](p8, p, 7)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)
      val p8V = read(p8, value, 7)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V, p8V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6),
      toColumnFormatter(p8, 7)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, P8, P9, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9) => T,
                                                               p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7], p8: CFoC[P8], p9: CFoC[P9]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(9 * 10)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)
      fields ++= write[P8](p8, p, 7)
      fields ++= write[P9](p9, p, 8)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)
      val p8V = read(p8, value, 7)
      val p9V = read(p9, value, 8)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V, p8V, p9V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6),
      toColumnFormatter(p8, 7),
      toColumnFormatter(p9, 8)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) => T,
                                                                    p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7], p8: CFoC[P8], p9: CFoC[P9], p10: CFoC[P10]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(10 * 11)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)
      fields ++= write[P8](p8, p, 7)
      fields ++= write[P9](p9, p, 8)
      fields ++= write[P10](p10, p, 9)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)
      val p8V = read(p8, value, 7)
      val p9V = read(p9, value, 8)
      val p10V = read(p10, value, 9)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V, p8V, p9V, p10V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6),
      toColumnFormatter(p8, 7),
      toColumnFormatter(p9, 8),
      toColumnFormatter(p10, 9)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) => T,
                                                                         p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7], p8: CFoC[P8], p9: CFoC[P9], p10: CFoC[P10], p11: CFoC[P11]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(11 * 12)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)
      fields ++= write[P8](p8, p, 7)
      fields ++= write[P9](p9, p, 8)
      fields ++= write[P10](p10, p, 9)
      fields ++= write[P11](p11, p, 10)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)
      val p8V = read(p8, value, 7)
      val p9V = read(p9, value, 8)
      val p10V = read(p10, value, 9)
      val p11V = read(p11, value, 10)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V, p8V, p9V, p10V, p11V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6),
      toColumnFormatter(p8, 7),
      toColumnFormatter(p9, 8),
      toColumnFormatter(p10, 9),
      toColumnFormatter(p11, 10)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) => T,
                                                                              p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7], p8: CFoC[P8], p9: CFoC[P9], p10: CFoC[P10], p11: CFoC[P11], p12: CFoC[P12]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(12 * 13)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)
      fields ++= write[P8](p8, p, 7)
      fields ++= write[P9](p9, p, 8)
      fields ++= write[P10](p10, p, 9)
      fields ++= write[P11](p11, p, 10)
      fields ++= write[P12](p12, p, 11)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)
      val p8V = read(p8, value, 7)
      val p9V = read(p9, value, 8)
      val p10V = read(p10, value, 9)
      val p11V = read(p11, value, 10)
      val p12V = read(p12, value, 11)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V, p8V, p9V, p10V, p11V, p12V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6),
      toColumnFormatter(p8, 7),
      toColumnFormatter(p9, 8),
      toColumnFormatter(p10, 9),
      toColumnFormatter(p11, 10),
      toColumnFormatter(p12, 11)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) => T,
                                                                                   p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7], p8: CFoC[P8], p9: CFoC[P9], p10: CFoC[P10], p11: CFoC[P11], p12: CFoC[P12], p13: CFoC[P13]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(13 * 14)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)
      fields ++= write[P8](p8, p, 7)
      fields ++= write[P9](p9, p, 8)
      fields ++= write[P10](p10, p, 9)
      fields ++= write[P11](p11, p, 10)
      fields ++= write[P12](p12, p, 11)
      fields ++= write[P13](p13, p, 12)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)
      val p8V = read(p8, value, 7)
      val p9V = read(p9, value, 8)
      val p10V = read(p10, value, 9)
      val p11V = read(p11, value, 10)
      val p12V = read(p12, value, 11)
      val p13V = read(p13, value, 12)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V, p8V, p9V, p10V, p11V, p12V, p13V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6),
      toColumnFormatter(p8, 7),
      toColumnFormatter(p9, 8),
      toColumnFormatter(p10, 9),
      toColumnFormatter(p11, 10),
      toColumnFormatter(p12, 11),
      toColumnFormatter(p13, 12)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) => T,
                                                                                        p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7], p8: CFoC[P8], p9: CFoC[P9], p10: CFoC[P10], p11: CFoC[P11], p12: CFoC[P12], p13: CFoC[P13], p14: CFoC[P14]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(14 * 15)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)
      fields ++= write[P8](p8, p, 7)
      fields ++= write[P9](p9, p, 8)
      fields ++= write[P10](p10, p, 9)
      fields ++= write[P11](p11, p, 10)
      fields ++= write[P12](p12, p, 11)
      fields ++= write[P13](p13, p, 12)
      fields ++= write[P14](p14, p, 13)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)
      val p8V = read(p8, value, 7)
      val p9V = read(p9, value, 8)
      val p10V = read(p10, value, 9)
      val p11V = read(p11, value, 10)
      val p12V = read(p12, value, 11)
      val p13V = read(p13, value, 12)
      val p14V = read(p14, value, 13)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V, p8V, p9V, p10V, p11V, p12V, p13V, p14V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6),
      toColumnFormatter(p8, 7),
      toColumnFormatter(p9, 8),
      toColumnFormatter(p10, 9),
      toColumnFormatter(p11, 10),
      toColumnFormatter(p12, 11),
      toColumnFormatter(p13, 12),
      toColumnFormatter(p14, 13)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) => T,
                                                                                             p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7], p8: CFoC[P8], p9: CFoC[P9], p10: CFoC[P10], p11: CFoC[P11], p12: CFoC[P12], p13: CFoC[P13], p14: CFoC[P14], p15: CFoC[P15]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(15 * 16)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)
      fields ++= write[P8](p8, p, 7)
      fields ++= write[P9](p9, p, 8)
      fields ++= write[P10](p10, p, 9)
      fields ++= write[P11](p11, p, 10)
      fields ++= write[P12](p12, p, 11)
      fields ++= write[P13](p13, p, 12)
      fields ++= write[P14](p14, p, 13)
      fields ++= write[P15](p15, p, 14)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)
      val p8V = read(p8, value, 7)
      val p9V = read(p9, value, 8)
      val p10V = read(p10, value, 9)
      val p11V = read(p11, value, 10)
      val p12V = read(p12, value, 11)
      val p13V = read(p13, value, 12)
      val p14V = read(p14, value, 13)
      val p15V = read(p15, value, 14)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V, p8V, p9V, p10V, p11V, p12V, p13V, p14V, p15V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6),
      toColumnFormatter(p8, 7),
      toColumnFormatter(p9, 8),
      toColumnFormatter(p10, 9),
      toColumnFormatter(p11, 10),
      toColumnFormatter(p12, 11),
      toColumnFormatter(p13, 12),
      toColumnFormatter(p14, 13),
      toColumnFormatter(p15, 14)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16) => T,
                                                                                                  p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7], p8: CFoC[P8], p9: CFoC[P9], p10: CFoC[P10], p11: CFoC[P11], p12: CFoC[P12], p13: CFoC[P13], p14: CFoC[P14], p15: CFoC[P15], p16: CFoC[P16]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(16 * 17)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)
      fields ++= write[P8](p8, p, 7)
      fields ++= write[P9](p9, p, 8)
      fields ++= write[P10](p10, p, 9)
      fields ++= write[P11](p11, p, 10)
      fields ++= write[P12](p12, p, 11)
      fields ++= write[P13](p13, p, 12)
      fields ++= write[P14](p14, p, 13)
      fields ++= write[P15](p15, p, 14)
      fields ++= write[P16](p16, p, 15)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)
      val p8V = read(p8, value, 7)
      val p9V = read(p9, value, 8)
      val p10V = read(p10, value, 9)
      val p11V = read(p11, value, 10)
      val p12V = read(p12, value, 11)
      val p13V = read(p13, value, 12)
      val p14V = read(p14, value, 13)
      val p15V = read(p15, value, 14)
      val p16V = read(p16, value, 15)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V, p8V, p9V, p10V, p11V, p12V, p13V, p14V, p15V, p16V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6),
      toColumnFormatter(p8, 7),
      toColumnFormatter(p9, 8),
      toColumnFormatter(p10, 9),
      toColumnFormatter(p11, 10),
      toColumnFormatter(p12, 11),
      toColumnFormatter(p13, 12),
      toColumnFormatter(p14, 13),
      toColumnFormatter(p15, 14),
      toColumnFormatter(p16, 15)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17) => T,
                                                                                                       p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7], p8: CFoC[P8], p9: CFoC[P9], p10: CFoC[P10], p11: CFoC[P11], p12: CFoC[P12], p13: CFoC[P13], p14: CFoC[P14], p15: CFoC[P15], p16: CFoC[P16], p17: CFoC[P17]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(17 * 18)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)
      fields ++= write[P8](p8, p, 7)
      fields ++= write[P9](p9, p, 8)
      fields ++= write[P10](p10, p, 9)
      fields ++= write[P11](p11, p, 10)
      fields ++= write[P12](p12, p, 11)
      fields ++= write[P13](p13, p, 12)
      fields ++= write[P14](p14, p, 13)
      fields ++= write[P15](p15, p, 14)
      fields ++= write[P16](p16, p, 15)
      fields ++= write[P17](p17, p, 16)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)
      val p8V = read(p8, value, 7)
      val p9V = read(p9, value, 8)
      val p10V = read(p10, value, 9)
      val p11V = read(p11, value, 10)
      val p12V = read(p12, value, 11)
      val p13V = read(p13, value, 12)
      val p14V = read(p14, value, 13)
      val p15V = read(p15, value, 14)
      val p16V = read(p16, value, 15)
      val p17V = read(p17, value, 16)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V, p8V, p9V, p10V, p11V, p12V, p13V, p14V, p15V, p16V, p17V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6),
      toColumnFormatter(p8, 7),
      toColumnFormatter(p9, 8),
      toColumnFormatter(p10, 9),
      toColumnFormatter(p11, 10),
      toColumnFormatter(p12, 11),
      toColumnFormatter(p13, 12),
      toColumnFormatter(p14, 13),
      toColumnFormatter(p15, 14),
      toColumnFormatter(p16, 15),
      toColumnFormatter(p17, 16)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18) => T,
                                                                                                            p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7], p8: CFoC[P8], p9: CFoC[P9], p10: CFoC[P10], p11: CFoC[P11], p12: CFoC[P12], p13: CFoC[P13], p14: CFoC[P14], p15: CFoC[P15], p16: CFoC[P16], p17: CFoC[P17], p18: CFoC[P18]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(18 * 19)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)
      fields ++= write[P8](p8, p, 7)
      fields ++= write[P9](p9, p, 8)
      fields ++= write[P10](p10, p, 9)
      fields ++= write[P11](p11, p, 10)
      fields ++= write[P12](p12, p, 11)
      fields ++= write[P13](p13, p, 12)
      fields ++= write[P14](p14, p, 13)
      fields ++= write[P15](p15, p, 14)
      fields ++= write[P16](p16, p, 15)
      fields ++= write[P17](p17, p, 16)
      fields ++= write[P18](p18, p, 17)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)
      val p8V = read(p8, value, 7)
      val p9V = read(p9, value, 8)
      val p10V = read(p10, value, 9)
      val p11V = read(p11, value, 10)
      val p12V = read(p12, value, 11)
      val p13V = read(p13, value, 12)
      val p14V = read(p14, value, 13)
      val p15V = read(p15, value, 14)
      val p16V = read(p16, value, 15)
      val p17V = read(p17, value, 16)
      val p18V = read(p18, value, 17)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V, p8V, p9V, p10V, p11V, p12V, p13V, p14V, p15V, p16V, p17V, p18V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6),
      toColumnFormatter(p8, 7),
      toColumnFormatter(p9, 8),
      toColumnFormatter(p10, 9),
      toColumnFormatter(p11, 10),
      toColumnFormatter(p12, 11),
      toColumnFormatter(p13, 12),
      toColumnFormatter(p14, 13),
      toColumnFormatter(p15, 14),
      toColumnFormatter(p16, 15),
      toColumnFormatter(p17, 16),
      toColumnFormatter(p18, 17)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19) => T,
                                                                                                                 p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7], p8: CFoC[P8], p9: CFoC[P9], p10: CFoC[P10], p11: CFoC[P11], p12: CFoC[P12], p13: CFoC[P13], p14: CFoC[P14], p15: CFoC[P15], p16: CFoC[P16], p17: CFoC[P17], p18: CFoC[P18], p19: CFoC[P19]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(19 * 20)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)
      fields ++= write[P8](p8, p, 7)
      fields ++= write[P9](p9, p, 8)
      fields ++= write[P10](p10, p, 9)
      fields ++= write[P11](p11, p, 10)
      fields ++= write[P12](p12, p, 11)
      fields ++= write[P13](p13, p, 12)
      fields ++= write[P14](p14, p, 13)
      fields ++= write[P15](p15, p, 14)
      fields ++= write[P16](p16, p, 15)
      fields ++= write[P17](p17, p, 16)
      fields ++= write[P18](p18, p, 17)
      fields ++= write[P19](p19, p, 18)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)
      val p8V = read(p8, value, 7)
      val p9V = read(p9, value, 8)
      val p10V = read(p10, value, 9)
      val p11V = read(p11, value, 10)
      val p12V = read(p12, value, 11)
      val p13V = read(p13, value, 12)
      val p14V = read(p14, value, 13)
      val p15V = read(p15, value, 14)
      val p16V = read(p16, value, 15)
      val p17V = read(p17, value, 16)
      val p18V = read(p18, value, 17)
      val p19V = read(p19, value, 18)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V, p8V, p9V, p10V, p11V, p12V, p13V, p14V, p15V, p16V, p17V, p18V, p19V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6),
      toColumnFormatter(p8, 7),
      toColumnFormatter(p9, 8),
      toColumnFormatter(p10, 9),
      toColumnFormatter(p11, 10),
      toColumnFormatter(p12, 11),
      toColumnFormatter(p13, 12),
      toColumnFormatter(p14, 13),
      toColumnFormatter(p15, 14),
      toColumnFormatter(p16, 15),
      toColumnFormatter(p17, 16),
      toColumnFormatter(p18, 17),
      toColumnFormatter(p19, 18)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20) => T,
                                                                                                                      p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7], p8: CFoC[P8], p9: CFoC[P9], p10: CFoC[P10], p11: CFoC[P11], p12: CFoC[P12], p13: CFoC[P13], p14: CFoC[P14], p15: CFoC[P15], p16: CFoC[P16], p17: CFoC[P17], p18: CFoC[P18], p19: CFoC[P19], p20: CFoC[P20]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(20 * 21)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)
      fields ++= write[P8](p8, p, 7)
      fields ++= write[P9](p9, p, 8)
      fields ++= write[P10](p10, p, 9)
      fields ++= write[P11](p11, p, 10)
      fields ++= write[P12](p12, p, 11)
      fields ++= write[P13](p13, p, 12)
      fields ++= write[P14](p14, p, 13)
      fields ++= write[P15](p15, p, 14)
      fields ++= write[P16](p16, p, 15)
      fields ++= write[P17](p17, p, 16)
      fields ++= write[P18](p18, p, 17)
      fields ++= write[P19](p19, p, 18)
      fields ++= write[P20](p20, p, 19)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)
      val p8V = read(p8, value, 7)
      val p9V = read(p9, value, 8)
      val p10V = read(p10, value, 9)
      val p11V = read(p11, value, 10)
      val p12V = read(p12, value, 11)
      val p13V = read(p13, value, 12)
      val p14V = read(p14, value, 13)
      val p15V = read(p15, value, 14)
      val p16V = read(p16, value, 15)
      val p17V = read(p17, value, 16)
      val p18V = read(p18, value, 17)
      val p19V = read(p19, value, 18)
      val p20V = read(p20, value, 19)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V, p8V, p9V, p10V, p11V, p12V, p13V, p14V, p15V, p16V, p17V, p18V, p19V, p20V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6),
      toColumnFormatter(p8, 7),
      toColumnFormatter(p9, 8),
      toColumnFormatter(p10, 9),
      toColumnFormatter(p11, 10),
      toColumnFormatter(p12, 11),
      toColumnFormatter(p13, 12),
      toColumnFormatter(p14, 13),
      toColumnFormatter(p15, 14),
      toColumnFormatter(p16, 15),
      toColumnFormatter(p17, 16),
      toColumnFormatter(p18, 17),
      toColumnFormatter(p19, 18),
      toColumnFormatter(p20, 19)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21) => T,
                                                                                                                           p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7], p8: CFoC[P8], p9: CFoC[P9], p10: CFoC[P10], p11: CFoC[P11], p12: CFoC[P12], p13: CFoC[P13], p14: CFoC[P14], p15: CFoC[P15], p16: CFoC[P16], p17: CFoC[P17], p18: CFoC[P18], p19: CFoC[P19], p20: CFoC[P20], p21: CFoC[P21]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(21 * 22)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)
      fields ++= write[P8](p8, p, 7)
      fields ++= write[P9](p9, p, 8)
      fields ++= write[P10](p10, p, 9)
      fields ++= write[P11](p11, p, 10)
      fields ++= write[P12](p12, p, 11)
      fields ++= write[P13](p13, p, 12)
      fields ++= write[P14](p14, p, 13)
      fields ++= write[P15](p15, p, 14)
      fields ++= write[P16](p16, p, 15)
      fields ++= write[P17](p17, p, 16)
      fields ++= write[P18](p18, p, 17)
      fields ++= write[P19](p19, p, 18)
      fields ++= write[P20](p20, p, 19)
      fields ++= write[P21](p21, p, 20)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)
      val p8V = read(p8, value, 7)
      val p9V = read(p9, value, 8)
      val p10V = read(p10, value, 9)
      val p11V = read(p11, value, 10)
      val p12V = read(p12, value, 11)
      val p13V = read(p13, value, 12)
      val p14V = read(p14, value, 13)
      val p15V = read(p15, value, 14)
      val p16V = read(p16, value, 15)
      val p17V = read(p17, value, 16)
      val p18V = read(p18, value, 17)
      val p19V = read(p19, value, 18)
      val p20V = read(p20, value, 19)
      val p21V = read(p21, value, 20)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V, p8V, p9V, p10V, p11V, p12V, p13V, p14V, p15V, p16V, p17V, p18V, p19V, p20V, p21V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6),
      toColumnFormatter(p8, 7),
      toColumnFormatter(p9, 8),
      toColumnFormatter(p10, 9),
      toColumnFormatter(p11, 10),
      toColumnFormatter(p12, 11),
      toColumnFormatter(p13, 12),
      toColumnFormatter(p14, 13),
      toColumnFormatter(p15, 14),
      toColumnFormatter(p16, 15),
      toColumnFormatter(p17, 16),
      toColumnFormatter(p18, 17),
      toColumnFormatter(p19, 18),
      toColumnFormatter(p20, 19),
      toColumnFormatter(p21, 20)
    )
  }

  def format[P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, T <: Product](construct: (P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22) => T,
                                                                                                                                p1: CFoC[P1], p2: CFoC[P2], p3: CFoC[P3], p4: CFoC[P4], p5: CFoC[P5], p6: CFoC[P6], p7: CFoC[P7], p8: CFoC[P8], p9: CFoC[P9], p10: CFoC[P10], p11: CFoC[P11], p12: CFoC[P12], p13: CFoC[P13], p14: CFoC[P14], p15: CFoC[P15], p16: CFoC[P16], p17: CFoC[P17], p18: CFoC[P18], p19: CFoC[P19], p20: CFoC[P20], p21: CFoC[P21], p22: CFoC[P22]): RootFormat[T] = new RootFormat[T] with Utils[T] {
    def write(p: T): CassandraObject = {
      val fields = new collection.mutable.ListBuffer[(String, CassandraValue)]
      fields.sizeHint(22 * 23)

      fields ++= write[P1](p1, p, 0)
      fields ++= write[P2](p2, p, 1)
      fields ++= write[P3](p3, p, 2)
      fields ++= write[P4](p4, p, 3)
      fields ++= write[P5](p5, p, 4)
      fields ++= write[P6](p6, p, 5)
      fields ++= write[P7](p7, p, 6)
      fields ++= write[P8](p8, p, 7)
      fields ++= write[P9](p9, p, 8)
      fields ++= write[P10](p10, p, 9)
      fields ++= write[P11](p11, p, 10)
      fields ++= write[P12](p12, p, 11)
      fields ++= write[P13](p13, p, 12)
      fields ++= write[P14](p14, p, 13)
      fields ++= write[P15](p15, p, 14)
      fields ++= write[P16](p16, p, 15)
      fields ++= write[P17](p17, p, 16)
      fields ++= write[P18](p18, p, 17)
      fields ++= write[P19](p19, p, 18)
      fields ++= write[P20](p20, p, 19)
      fields ++= write[P21](p21, p, 20)
      fields ++= write[P22](p22, p, 21)

      CassandraObject(fields.toMap)
    }

    def read(value: CassandraValue): T = {
      val p1V = read(p1, value, 0)
      val p2V = read(p2, value, 1)
      val p3V = read(p3, value, 2)
      val p4V = read(p4, value, 3)
      val p5V = read(p5, value, 4)
      val p6V = read(p6, value, 5)
      val p7V = read(p7, value, 6)
      val p8V = read(p8, value, 7)
      val p9V = read(p9, value, 8)
      val p10V = read(p10, value, 9)
      val p11V = read(p11, value, 10)
      val p12V = read(p12, value, 11)
      val p13V = read(p13, value, 12)
      val p14V = read(p14, value, 13)
      val p15V = read(p15, value, 14)
      val p16V = read(p16, value, 15)
      val p17V = read(p17, value, 16)
      val p18V = read(p18, value, 17)
      val p19V = read(p19, value, 18)
      val p20V = read(p20, value, 19)
      val p21V = read(p21, value, 20)
      val p22V = read(p22, value, 21)

      construct(p1V, p2V, p3V, p4V, p5V, p6V, p7V, p8V, p9V, p10V, p11V, p12V, p13V, p14V, p15V, p16V, p17V, p18V, p19V, p20V, p21V, p22V)
    }

    override val columnFormatter: Map[String, CassandraFormat[_]] = Map(
      toColumnFormatter(p1, 0),
      toColumnFormatter(p2, 1),
      toColumnFormatter(p3, 2),
      toColumnFormatter(p4, 3),
      toColumnFormatter(p5, 4),
      toColumnFormatter(p6, 5),
      toColumnFormatter(p7, 6),
      toColumnFormatter(p8, 7),
      toColumnFormatter(p9, 8),
      toColumnFormatter(p10, 9),
      toColumnFormatter(p11, 10),
      toColumnFormatter(p12, 11),
      toColumnFormatter(p13, 12),
      toColumnFormatter(p14, 13),
      toColumnFormatter(p15, 14),
      toColumnFormatter(p16, 15),
      toColumnFormatter(p17, 16),
      toColumnFormatter(p18, 17),
      toColumnFormatter(p19, 18),
      toColumnFormatter(p20, 19),
      toColumnFormatter(p21, 20),
      toColumnFormatter(p22, 21)
    )
  }
}
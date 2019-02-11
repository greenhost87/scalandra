package com.github.greenhost87.scalandra.formats

trait TupleFormats {
  this: BasicFormats =>

  import PimpedRow._

  implicit def tuple1Format[A: CassandraFormat]: CassandraFormat[Tuple1[A]] = new CassandraFormat[Tuple1[A]] {
    def write(t: Tuple1[A]): CassandraValue = t._1.asCV

    def read(value: CassandraValue) = Tuple1(value.convertTo[A])
  }

  implicit def tuple2Format[A: CassandraFormat, B: CassandraFormat]: RootFormat[(A, B)] = new RootFormat[(A, B)] {
    def write(t: (A, B)) = CassandraArray(t._1.asCV, t._2.asCV)

    def read(value: CassandraValue): (A, B) = value match {
      case CassandraArray(Seq(a, b)) => (a.convertTo[A], b.convertTo[B])

      case x => deserializationError("Expected Tuple2 as CassandraArray, but got " + x)
    }
  }

  implicit def tuple3Format[A: CassandraFormat, B: CassandraFormat, C: CassandraFormat]: RootFormat[(A, B, C)] = new RootFormat[(A, B, C)] {
    def write(t: (A, B, C)) = CassandraArray(t._1.asCV, t._2.asCV, t._3.asCV)

    def read(value: CassandraValue): (A, B, C) = value match {
      case CassandraArray(Seq(a, b, c)) => (a.convertTo[A], b.convertTo[B], c.convertTo[C])
      case x => deserializationError("Expected Tuple3 as CassandraArray, but got " + x)
    }
  }

  implicit def tuple4Format[A: CassandraFormat, B: CassandraFormat, C: CassandraFormat, D: CassandraFormat]: RootFormat[(A, B, C, D)] = new RootFormat[(A, B, C, D)] {
    def write(t: (A, B, C, D)) = CassandraArray(t._1.asCV, t._2.asCV, t._3.asCV, t._4.asCV)

    def read(value: CassandraValue): (A, B, C, D) = value match {
      case CassandraArray(Seq(a, b, c, d)) => (a.convertTo[A], b.convertTo[B], c.convertTo[C], d.convertTo[D])
      case x => deserializationError("Expected Tuple4 as CassandraArray, but got " + x)
    }
  }

  implicit def tuple5Format[A: CassandraFormat, B: CassandraFormat, C: CassandraFormat, D: CassandraFormat, E: CassandraFormat]: RootFormat[(A, B, C, D, E)] = {
    new RootFormat[(A, B, C, D, E)] {
      def write(t: (A, B, C, D, E)) = CassandraArray(t._1.asCV, t._2.asCV, t._3.asCV, t._4.asCV, t._5.asCV)

      def read(value: CassandraValue): (A, B, C, D, E) = value match {
        case CassandraArray(Seq(a, b, c, d, e)) =>
          (a.convertTo[A], b.convertTo[B], c.convertTo[C], d.convertTo[D], e.convertTo[E])
        case x => deserializationError("Expected Tuple5 as CassandraArray, but got " + x)
      }
    }
  }

  implicit def tuple6Format[A: CassandraFormat, B: CassandraFormat, C: CassandraFormat, D: CassandraFormat, E: CassandraFormat, F: CassandraFormat]: RootFormat[(A, B, C, D, E, F)] = {
    new RootFormat[(A, B, C, D, E, F)] {
      def write(t: (A, B, C, D, E, F)) = CassandraArray(t._1.asCV, t._2.asCV, t._3.asCV, t._4.asCV, t._5.asCV, t._6.asCV)

      def read(value: CassandraValue): (A, B, C, D, E, F) = value match {
        case CassandraArray(Seq(a, b, c, d, e, f)) =>
          (a.convertTo[A], b.convertTo[B], c.convertTo[C], d.convertTo[D], e.convertTo[E], f.convertTo[F])
        case x => deserializationError("Expected Tuple6 as CassandraArray, but got " + x)
      }
    }
  }

  implicit def tuple7Format[A: CassandraFormat, B: CassandraFormat, C: CassandraFormat, D: CassandraFormat, E: CassandraFormat, F: CassandraFormat, G: CassandraFormat] = {
    new RootFormat[(A, B, C, D, E, F, G)] {
      def write(t: (A, B, C, D, E, F, G)) = CassandraArray(t._1.asCV, t._2.asCV, t._3.asCV, t._4.asCV, t._5.asCV, t._6.asCV, t._7.asCV)

      def read(value: CassandraValue): (A, B, C, D, E, F, G) = value match {
        case CassandraArray(Seq(a, b, c, d, e, f, g)) =>
          (a.convertTo[A], b.convertTo[B], c.convertTo[C], d.convertTo[D], e.convertTo[E], f.convertTo[F], g.convertTo[G])
        case x => deserializationError("Expected Tuple7 as CassandraArray, but got " + x)
      }
    }
  }
}

package com.github.greenhost87.scalandra.requests.utils

import scala.util.control.NoStackTrace

trait CassandraError extends NoStackTrace


trait ReadError extends CassandraError

case class CassandraProblem(message: String) extends WriteError with ReadError

trait NotFound extends ReadError

case object NotFound extends NotFound


trait WriteError extends CassandraError

case object WriteFailed extends WriteError


trait Succeed

case object Succeed extends Succeed
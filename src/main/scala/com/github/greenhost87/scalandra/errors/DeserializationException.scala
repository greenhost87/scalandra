package com.github.greenhost87.scalandra.errors

case class DeserializationException(msg: String,
                                    cause: Throwable = null,
                                    fieldNames: List[String] = Nil) extends RuntimeException(msg, cause)
package com.github.greenhost87.scalandra.requests.utils

object EqualityEnum extends Enumeration {
  type Equality = Value

  case class EqualityValue(symbol: String) extends super.Val {
    override def toString: String =
      symbol
  }

  val `>` = EqualityValue(">")
  val `<` = EqualityValue("<")
  val `=` = EqualityValue("=")
}

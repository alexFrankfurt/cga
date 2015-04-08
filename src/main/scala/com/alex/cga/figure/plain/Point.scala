package com.alex.cga
package figure
package plain



case class Point(x: Double, y: Double) extends PlainFigure{
  import Point._
  type Relation = Value
}

object Point extends Enumeration{
  val In, On, Out = Value
}

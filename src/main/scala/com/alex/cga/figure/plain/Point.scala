package com.alex.cga
package figure
package plain



case class Point(x: Int, y: Int) extends PlainFigure{
  import Point._
  type Relation = Value
}

object Point extends Enumeration{
  val On, Out = Value
}

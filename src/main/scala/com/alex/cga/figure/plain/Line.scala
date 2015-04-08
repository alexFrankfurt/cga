package com.alex.cga
package figure
package plain



case class Line(p1: Point, p2: Point) extends PlainFigure{
  import Line._
  type Relation = Value
}

object Line extends Enumeration{
  val On, TheSame, Parallel, Intersect = Value
}


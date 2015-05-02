package com.alex.cga
package geometry
package plain



case class Line(p1: Point, p2: Point) extends TwoPointEntity(p1, p2) with Figure{
  import Line._
  type Relation = Value
}

object Line extends Enumeration{
  val On, TheSame, Parallel, Intersect = Value
}


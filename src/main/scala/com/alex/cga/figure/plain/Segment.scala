package com.alex.cga
package figure
package plain



case class Segment(p1: Point, p2: Point) extends TwoPointEntity(p1, p2) with PlainFigure{
  import Segment._
  type Relation = Value

  def toLine = Line(p1, p2)
  def toFreeVector = FreeVector(p1, p2)
}

object Segment extends Enumeration {
  val On, Out, Intersect, NotIntersect = Value
}

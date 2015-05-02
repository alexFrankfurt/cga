package com.alex.cga
package geometry
package plain



case class Segment(p1: Point, p2: Point) extends TwoPointEntity(p1, p2) with Figure{
  import Segment._
  type Relation = Value

  def toLine = Line(p1, p2)
  def toFreeVector = FreeVector(p1, p2)
  override def toString = Segment.getClass.getName + "(" + p1 + ", " + p2 + ")"
}

object Segment extends Enumeration {
  val On, Out, Intersect, NotIntersect = Value
}

package com.alex.cga
package figure
package plain



case class Segment(p1: Point, p2: Point) extends PlainFigure{
  import Segment._
  type Relation = Value
}

object Segment extends Enumeration {
  val On, Out = Value
}

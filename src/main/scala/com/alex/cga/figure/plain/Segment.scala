package com.alex.cga
package figure
package plain



case class Segment(p1: Point, p2: Point) extends PlainFigure{

}

object Segment extends Enumeration {
  val On, Out = Value
}

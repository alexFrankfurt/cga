package com.alex.cga
package figure
package plain



case class Line(p1: Point, p2: Point) extends PlainFigure{

}

object Line extends Enumeration{
  val On = Value
}


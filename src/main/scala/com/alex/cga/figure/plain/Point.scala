package com.alex.cga
package figure
package plain



case class Point(x: Double, y: Double) extends PlainFigure{
  import Point._
  type Relation = Value

  def +(that: Direction) = new Point(x + that.x, y + that.y)
  def -(that: Direction) = new Point(x - that.x, y - that.y)
  override def toString = "Point(" + x.toString + ", " + y + ")"
}

object Point extends Enumeration{
  val In, On, Out = Value
  val Lefter, Righter, OnBorder, Failure = Value
}

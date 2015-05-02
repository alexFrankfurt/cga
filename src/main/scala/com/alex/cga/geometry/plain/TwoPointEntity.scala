package com.alex.cga.geometry.plain

import scala.math.sqrt

class TwoPointEntity(p1: Point, p2: Point) {
  val x = p2.x - p1.x
  val y = p2.y - p1.y

  def length = {
    sqrt(x * x + y * y)
  }
}

object TwoPointEntity{
  def apply(p1: Point, p2: Point) = new TwoPointEntity(p1, p2)
}

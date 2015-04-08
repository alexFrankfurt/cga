package com.alex.cga.figure

import plain.Point
import math.sqrt, math.abs

class TwoPointEntity(p1: Point, p2: Point) {
  val x = p2.x - p1.x
  val y = p2.y - p1.y

  def length = {
    sqrt(x * x + y * y)
  }
}

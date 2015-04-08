package com.alex.cga.figure

import plain.Point

class FreeVector(p1: Point, p2: Point) extends TwoPointEntity(p1, p2) {
  def *(that: FreeVector) = x * that.x + y * that.y
  def || = length
}

object FreeVector {
  def apply(p1: Point, p2: Point) = new FreeVector(p1, p2)
}

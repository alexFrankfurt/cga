package com.alex.cga.figure



case class Direction(x: Double, y: Double) {
  def *(that: Int) = new Direction(x * that, y * that)
}

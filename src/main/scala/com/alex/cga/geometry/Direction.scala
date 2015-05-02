package com.alex.cga.geometry



case class Direction(x: Double, y: Double) {
  def *(that: Int) = new Direction(x * that, y * that)
}

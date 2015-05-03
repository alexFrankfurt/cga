package com.alex.cga.sugar

class SquareBound(
    xMin: Double,
    xMax: Double,
    yMin: Double,
    yMax: Double) {
  def xRange = (xMin, xMax)
  def yRange = (yMin, yMax)
}

object SquareBound {
  def apply(xMin: Double, xMax: Double, yMin: Double, yMax: Double) =
    new SquareBound(xMin, xMax, yMin, yMax)
}

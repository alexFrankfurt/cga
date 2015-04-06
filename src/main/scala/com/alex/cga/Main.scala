package com.alex.cga

import figure.plain._
import algorithm.PlainFigureRelation._
import algorithm.binaryTest

object Main {
  def main(args: Array[String]) {
    val p1 = Point(1, 3)
    val p2 = Point(0, 2)
    val p3 = Point(-19, 10)
    val pol2 = ConvexPolygon(p3, p2, p1)
    println(p1 R pol2)
  }
}

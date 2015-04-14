package com.alex.cga

import java.util.logging.{Level, Logger}

import figure.DoubleVector
import figure.plain._

import scala.collection.mutable

object Container {
  type ActivePoint = (Point, DoubleVector)

  val logger = Logger.getLogger(Container.getClass.getName)

  val p1 = Point(0, 0)
  val p2 = Point(-3, -6)
  val p3 = Point(0, -3)
  val p4 = Point(10, 0)
  val p5 = Point(15, 10)
  val pol = ConcavePolygon(p1, p2, p3, p4, p5)

  val d1 = DoubleVector(1.1, 2.2)
  val d2 = DoubleVector(-1.2, 0.2)
  val d3 = DoubleVector(0.3, 0.8)

  val pts: mutable.MutableList[ActivePoint] =
    mutable.MutableList((p1, d1), (p2, d2), (p3, d3))
}

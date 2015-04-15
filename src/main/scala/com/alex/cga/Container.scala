package com.alex.cga

import java.util.logging.{Level, Logger}

import figure.Direction
import figure.plain._

import scala.collection.mutable

object Container {
//  type ActivePoint = (Point, DoubleVector)

  val logger = Logger.getLogger(Container.getClass.getName)

  val p1 = Point(0, 0)
  val p2 = Point(-3, -6)
  val p3 = Point(0, -3)
  val p4 = Point(10, 0)
  val p5 = Point(15, 10)
  val innerPolygon = ConcavePolygon(p1, p2, p3, p4, p5)

  val op1 = Point(30, 40)
  val op2 = Point(0, 50)
  val op3 = Point(-30, 40)
  val op4 = Point(-30, -40)
  val op5 = Point(0, -50)
  val op6 = Point(30, -40)
  val outerPolygon = ConvexPolygon(op1, op2, op3, op4, op5, op6)

  val d1 = Direction(1.1, 2.2)
  val d2 = Direction(-1.2, 0.2)
  val d3 = Direction(0.3, 0.8)

  val (fp1, fp2) = (Point(0.3, 0.8), Point(2, 3.3))
  val (pd1, pd2) = (Direction(0.3, 0.2), Direction(-0.4, 0.2))
  val (fp3, fp4) = (Point(1.1, -2.3), Point(3.8, 10))
  val (pd3, pd4) = (Direction(-0.3, 0.2), Direction(0.3, 0.1))

  var points = List(fp1, fp2, fp3, fp4)
  var directions = List(pd1, pd2, pd3, pd4)


//  val pts: mutable.MutableList[ActivePoint] =
//    mutable.MutableList((p1, d1), (p2, d2), (p3, d3))
}

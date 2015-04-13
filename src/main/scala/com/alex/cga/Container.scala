package com.alex.cga

import java.util.logging.{Level, Logger}

import figure.plain._

object Container {
  val logger = Logger.getLogger(Container.getClass.getName)

  val p1 = Point(0, 0)
  val p2 = Point(-3, -6)
  val p3 = Point(0, -3)
  val p4 = Point(10, 0)
  val p5 = Point(15, 10)
  val pol = ConcavePolygon(p1, p2, p3, p4, p5)

  val pList = List(p1, p2, p3)

  implicit class MovablePoint(var points: List[Point]) {
    def moved = {
      points = points.map { p =>
        Point(p.x + 1, p.y + 1)
      }
      logger.log(Level.INFO, s"new points: $points")
      points
    }
  }
}

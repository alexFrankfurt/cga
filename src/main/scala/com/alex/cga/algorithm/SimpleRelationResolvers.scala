package com.alex.cga
package algorithm

import figure.PlainPolygon
import figure.plain.Point

object SimpleRelationResolvers {
  implicit class DoubleInRange(x: Double) {
    def in(coordinates: (Double, Double)): Boolean =
      coordinates._1 <= x && x <= coordinates._2
  }

  implicit class PointInSquare(p: Point) {
    def in(squareBound: SquareBound): Boolean =
      (p.x in squareBound.xRange) && (p.y in squareBound.yRange)
  }

  implicit class PolygonBoundSquare(pol: PlainPolygon) {
    def boundSquare: SquareBound = {
      val pointList = pol.vertices.list

      val xMin = pointList./:(pointList.head.x)(_ min _.x)

      val xMax = pointList./:(pointList.head.x)(_ max _.x)

      val yMin = pointList./:(pointList.head.y)(_ min _.y)

      val yMax = pointList./:(pointList.head.y)(_ max _.y)

      SquareBound(xMin, xMax, yMin, yMax)
    }
  }
}

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
      val xMin = pointList./:(pointList.head.x) {
        (x, p) => x min p.x
      }

      val xMax = pointList./:(pointList.head.x) {
        (x, p) => x max p.x
      }

      val yMin = pointList./:(pointList.head.y) {
        (y, p) => y min p.y
      }

      val yMax = pointList./:(pointList.head.y) {
        (y, p) => y max p.y
      }

      SquareBound(xMin, xMax, yMin, yMax)
    }
  }
}

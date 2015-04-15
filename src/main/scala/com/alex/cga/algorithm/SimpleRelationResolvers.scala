package com.alex.cga
package algorithm

import PlainFigureRelation._
import com.alex.cga.figure.{FreeVector, Direction, PlainPolygon}
import figure.plain.{Point, Segment}

import math.abs
import annotation.tailrec

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

  implicit class SegmentIntersectPolygon(seg: Segment) {
    def intersect(pol: PlainPolygon) = {
      val vertices = pol.vertices
      val length = vertices.length
      @tailrec
      def loop(ind: Int): Option[Segment] = {
        val side = Segment(vertices(ind), vertices(ind + 1))
        if (ind == length) None
        else if ((side R seg) == Segment.Intersect)
          Some(side)
        else loop(ind + 1)
      }
      loop(0)
    }
  }

  implicit val intToFreeVector = (x: Double) => FreeVector(x, x)
  implicit val freeVectorToDirection = (v: FreeVector) => Direction(v.x, v.y)

  implicit class SegmentHitSegment(v: Segment) {
    def hit(b: Segment): Direction = {
      val bv = b.toFreeVector
      val vv = v.toFreeVector
      2 * abs((vv * bv) / (bv * bv)) * bv - vv
    }
  }
}

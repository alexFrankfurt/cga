package com.alex.cga
package algorithm

import PlainFigureRelation._
import geometry.Direction
import geometry.plain.{Polygon, FreeVector, Point, Segment}
import Extensions.SeqPointMinMax

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

  implicit class SegmentIntersectPolygon(seg: Segment) {
    def intersect(pol: Polygon) = {
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

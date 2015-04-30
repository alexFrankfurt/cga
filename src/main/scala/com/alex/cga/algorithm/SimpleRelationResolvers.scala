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
      val pl = pol.vertices.list
      SquareBound(pl.xMin, pl.xMax, pl.yMin, pl.yMax)
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

  implicit class SeqPointMinMax(seq: Seq[Point]) {
    def xMin = seq./:(seq.head.x)(_ min _.x)

    def xMax = seq./:(seq.head.x)(_ max _.x)

    def yMin = seq./:(seq.head.y)(_ min _.y)

    def yMax = seq./:(seq.head.y)(_ max _.y)

    def xyMin = seq reduce { (p1, p2) =>
      if (p2.x <= p1.x && p2.y <= p1.y) p2
      else p1
    }

    def xyMax = seq reduce { (p1, p2) =>
      if (p2.x >= p1.x && p2.y >= p1.y) p2
      else p1
    }
  }
}

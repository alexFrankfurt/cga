package com.alex.cga

import figure.{FreeVector, Angle}
import figure.plain.{Segment, ConvexPolygon, ConcavePolygon, Point, Line}
import math.{acos, Pi}
import algorithm.SimpleRelationResolvers._
import algorithm.PlainFigureRelation._

import scala.annotation.tailrec

package object algorithm {
  implicit val binaryTest = (point: Point, polygon: ConvexPolygon) => {
    import Point._

    if (point in polygon.boundSquare) {
      val pointList = polygon.vertices
      val firstPoint = pointList.head
      val middlePoint = pointList(pointList.length / 2)
      val innerPoint = Point(
        (firstPoint.x + middlePoint.x) / 2,
        (firstPoint.y + middlePoint.y) / 2
      )
      val (start, end) = pointSector(point,
                  pointList,
                  innerPoint,
                  1,
                  pointList.length,
                  pointList.length / 2)
      val edge = Segment(pointList(start), pointList(end))
      val zq = Segment(innerPoint, point)

      if ((edge R zq) == Segment.NotIntersect) In
      else Out
    } else Out
  }

  @tailrec
  def pointSector(point: Point, list: Circle[Point], innerPoint: Point, start: Int, end: Int, sep: Int): (Int, Int) = {
    if (end - start == 1) (start, end)
    else if (∠(list(start), innerPoint, list(sep)) > ∠(list(start), innerPoint, point))
      pointSector(point, list, innerPoint, start, sep, (start + sep) / 2)
    else pointSector(point, list, innerPoint, sep, end, (sep + end) / 2)
  }

  implicit val octaneTest = (point: Point, polygon: ConcavePolygon) => {
    import Point._
    if (point in polygon.boundSquare) {
      In
    } else Out
  }

  implicit val segToSeg: (Segment, Segment) => Segment#Relation =
    (segment1: Segment, segment2: Segment) => {
    val lineRelation = lineToLine(segment1.toLine, segment2.toLine)
    if (lineRelation == Line.Parallel) 
      Segment.NotIntersect
    else if (lineRelation == Line.TheSame) {
      if (segment1.p1.x == segment1.p2.x){
        if ((segment1.p1.y in (segment2.p1.y, segment2.p2.y)) ||
            (segment1.p2.y in (segment2.p1.y, segment2.p2.y)) ||
            (segment2.p1.y in (segment1.p1.y, segment1.p2.y)) ||
            (segment2.p2.y in (segment1.p1.y, segment1.p2.y)))
          Segment.Intersect
        else Segment.NotIntersect
      } else {
        if ((segment1.p1.x in (segment2.p1.x, segment2.p2.x)) ||
            (segment1.p2.x in (segment2.p1.x, segment2.p2.x)) ||
            (segment2.p1.x in (segment1.p1.x, segment1.p2.x)) ||
            (segment2.p2.x in (segment1.p1.x, segment1.p2.x)))
          Segment.Intersect
        else Segment.NotIntersect
      }
    } else {
      if (||(segment1.p1, segment1.p2, segment1.p1, segment2.p1) *
          ||(segment1.p1, segment1.p2, segment1.p1, segment2.p2) > 0)
        Segment.NotIntersect
      else if (||(segment2.p1, segment2.p2, segment2.p1, segment1.p1) *
               ||(segment2.p1, segment2.p2, segment2.p1, segment1.p2) > 0)
        Segment.NotIntersect
      else Segment.Intersect
    }
  }

  implicit val lineToLine = (line1: Line, line2: Line) => {
    import Line._
    if (||(line1.p1, line1.p2, line2.p1, line2.p2) == 0)
      if (||(line1.p1, line1.p2, line1.p1, line2.p1) == 0) TheSame
      else Parallel
    else Intersect
  }

  def ∠(p1: Point, p2: Point, p3: Point) = {
    val v1 = FreeVector(p2, p1)
    val v2 = FreeVector(p2, p3)
    val alpha = acos((v1 * v2) / (v1.length * v2.length))
    val det = ||(p2, p1, p2, p3)
    if (det > 0) Angle(alpha)
    else if (det == 0) Angle(alpha)
    else Angle(2 * Pi - alpha)
  }

  def ||(v1: FreeVector, v2: FreeVector): Double = v2.y * v1.x - v2.x * v1.y

  def ||(p1: Point, p2: Point, p3: Point, p4: Point): Double =
    ||(FreeVector(p1, p2), FreeVector(p3, p4))
}

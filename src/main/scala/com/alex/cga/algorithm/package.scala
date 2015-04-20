package com.alex.cga

import figure.{TwoPointEntity, FreeVector, Angle}
import figure.plain.{Segment, ConvexPolygon, ConcavePolygon, Point, Line}
import math.{acos, Pi}
import algorithm.SimpleRelationResolvers._
import algorithm.PlainFigureRelation._

import scala.annotation.tailrec
import math.abs

package object algorithm {
  implicit val binaryTest:(Point, ConvexPolygon) => Point#Relation = (point, polygon) => {
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
                                     0,
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

  implicit val octaneTest: (Point, ConcavePolygon) => Point#Relation = (q, polygon) => {
    import Point._
    if (q in polygon.boundSquare) {
      val (sum, onBorder: Boolean) = octaneStep(0,
                                       polygon.vertices.length,
                                       q,
                                       polygon.vertices,
                                       0,
                                       onBorder = false)

      if (onBorder) OnBorder
      else {
        sum match {
          case s if abs(s) == 8 => In
          case s if s == 0 => Out
          case _ => Failure
        }
      }
    } else Out
  }

  @tailrec
  def octaneStep(ind: Int, len: Int, q: Point, list: Circle[Point], sum: Int, onBorder: Boolean): (Int, Boolean) = {
    import Point._
    if (ind >= len) (sum, onBorder)
    else {
      val vi = Line(q, list(ind))
      val `vi+1` = TwoPointEntity(q, list(ind + 1))
      val di = octane(vi)
      val `di+1` = octane(`vi+1`)

      val a = `di+1` - di match {
        case x if x > 4 => x - 8
        case x if x < -4 => x + 8
        case x if abs(x) == 4 => list(ind + 1) match {
          case pi1 if (pi1 R vi) == Lefter => 4
          case pil if (pil R vi) == Righter => -4
          case _ => true
        }
        case x => x
      }
      a match {
        case true => (ind, true)
        case x: Int => octaneStep(ind + 1, len, q, list, sum + x, onBorder)
      }
    }
  }

  def octane(vector: TwoPointEntity): Int = {
    val x = vector.x
    val y = vector.y
    octane(Point(x, y))
  }

  def octane(p: Point): Int = {
    val x = p.x
    val y = p.y

         if ( 0  <  y &&  y <=  x) 1
    else if ( 0 <=  x &&  x  <  y) 2
    else if ( 0  < -x && -x <=  y) 3
    else if ( 0 <=  y &&  y  < -x) 4
    else if ( 0  < -y && -y <= -x) 5
    else if ( 0 <= -x && -x  < -y) 6
    else if ( 0  <  x &&  x <= -y) 7
    else if ( 0 <= -y && -y  <  x) 8
    else -1
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

  implicit val pointToLine: (Point, Line) => Point#Relation = (p, l) => {
    import Point._
    ||(l.p1, l.p2, l.p1, p) match {
      case x if x > 0 => Lefter
      case x if x < 0 => Righter
      case _ => On
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

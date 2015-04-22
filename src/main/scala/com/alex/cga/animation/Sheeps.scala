package com.alex.cga.animation

import java.awt.Graphics2D
import java.awt.geom.{Ellipse2D, Line2D}

import com.alex.cga.algorithm
import algorithm.PlainFigureRelation._
import algorithm.SimpleRelationResolvers._
import algorithm.{binaryTest, octaneTest}
import com.alex.cga.figure.{Direction, PlainPolygon}
import com.alex.cga.figure.plain.{Segment, ConcavePolygon, ConvexPolygon, Point}
import Point._

import scala.annotation.tailrec

trait Sheeps{
  val cs: CoordinateCenter
  val innerPolygon: ConcavePolygon
  val outerPolygon: ConvexPolygon

  def drawAll(points: List[Point])(implicit g: Graphics2D): Unit = {
    drawPolygons
    draw(points)
  }

  def findNext(directions: List[Direction], points: List[Point]) = {
    @tailrec
    def loop
    (pts: List[Point], drs: List[Direction], npts: List[Point], ndrs: List[Direction]):
    (List[Point], List[Direction]) = {
      if (pts.isEmpty) (npts, ndrs)
      else {
        val oldP :: oldPts = pts
        val oldD :: oldDrs = drs
        val newP = oldP + oldD
        val pVector = Segment(oldP, newP)
        val rel = (newP R innerPolygon, newP R outerPolygon)

        rel match {
          case (Out, In) => loop(oldPts, oldDrs, newP :: npts, oldD :: ndrs)
          case (In, _) => loop(oldPts, oldDrs, npts, ndrs)
          case (_, Out) =>
            val intersectedSegment = (pVector intersect outerPolygon).orNull
            val newD = pVector hit intersectedSegment
            loop(oldPts, oldDrs, oldP - oldD * 2 :: npts, newD :: ndrs)
        }
      }
    }
    loop(points, directions, List(), List())
  }

  def drawPolygons(implicit g: Graphics2D): Unit = {
    drawPolygon(innerPolygon)
    drawPolygon(outerPolygon)
  }

  def drawPolygon(pol: PlainPolygon)(implicit g: Graphics2D): Unit = {
    for (i <- 0 to pol.num) {
      val pi = pol.vertices(i)
      val `pi+1` = pol.vertices(i + 1)
      g.draw(new Line2D.Double(cs.x + pi.y, cs.x + pi.x, cs.y + `pi+1`.y, cs.y + `pi+1`.x))
    }
  }

  def draw(points: List[Point])(implicit g: Graphics2D): Unit = {
    for (i <- 0 until points.length) {
      val pi = points(i)
      g draw {
        new Ellipse2D.Double(cs.y + pi.y, cs.x + pi.x, 3, 3)
      }
    }
  }
}

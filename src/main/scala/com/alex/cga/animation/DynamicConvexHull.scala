package com.alex.cga
package animation

import java.awt.Graphics2D

import com.alex.cga.geometry.plain.{Line, ConvexPolygon, Point}
import collection.mutable
import sugar.Extensions.{draw => drawFigure}
import Container.{randPoint, logger}
import algorithm.PlainFigureRelation._
import algorithm.{binaryTest, pointToLine}
import Point._
import sugar.Extensions._

import scala.annotation.tailrec

class DynamicConvexHull { self: Animatable with MakeAnimation with DrawGraphics2DAnimation =>
  import DynamicConvexHull.PointsWithConvexHull
  type DynamicState = PointsWithConvexHull

  val initPoint = randPoint(200)
  val delay = 500
  val initialState = (Vector[Point](initPoint), Vector[Point](initPoint))

  def draw(ds: DynamicState)(implicit g: Graphics2D) = {
    val (pts, ch) = ds

    drawFigure(pts)
    drawFigure(ConvexPolygon(ch: _*))
  }

  def findNewState(ind: Int) = {
    val (pts, ch) = animation(ind)
    val np = randPoint(200)

    if (ch.length == 1) {
      if (pts.head == np) (pts, ch)
      else (pts :+ np, ch :+ np)
    }
    else if (ch.length == 2){
      val rel = np R Line(pts.head, pts(1))
      rel match {
        case On =>
          val buf = pts :+ np
          val rp = buf.xyMax
          val lp = buf.xyMin
          (buf, Vector(lp, rp))
        case Lefter =>
          (pts :+ np, Vector(pts.head, pts(1), np))
        case Righter =>
          (pts :+ np, Vector(pts.head, np, pts(1)))
      }
    }
    else {
      val chp = ConvexPolygon(ch: _*)
      (pts :+ np, np R chp match {
        case In => ch
        case Out => updateCh(np, ch)
      })
    }
  }

  def updateCh(np: Point, ch: Vector[Point]) = {
    val chp = ConvexPolygon(ch: _*)
    val ins = (for {
      (side, i) <- chp.sides.view.zipWithIndex
      if (np R side.toLine) == Righter
    } yield i).force.toVector

    val moveInd =
      if (ins.contains(0) && ins.contains(ch.length - 1)) Some(getMoveInd(ins))
      else None

    moveInd match {
      case None =>
        val resl = ch.take(ins.head + 1)
        val resr = ch.takeRight(ch.length - ins.last - 1)
        (resl :+ np) ++ resr
      case Some(ind) =>
        val newCh = moveElements(ch, ind)
        val chp = ConvexPolygon(newCh: _*)
        val ins = (for {
          (side, i) <- chp.sides.view.zipWithIndex
          if (np R side.toLine) == Righter
        } yield i).force.toVector
        val resl = newCh.take(ins.head + 1)
        val resr = newCh.takeRight(ch.length - ins.last - 1)
        (resl :+ np) ++ resr
    }
  }

  def getMoveInd(ins: Vector[Int]) = {
    @tailrec
    def loop(cur: Int, next: Int): Int = {
      val diff = ins(next) - ins(cur)
      if (diff == 1) loop(next, next + 1)
      else cur + 1
    }
    loop(0, 1)
  }

  def moveElements(ch: Vector[Point], ind: Int) = {
    val len = ch.length
    val circle = Circle(ch)
    @tailrec
    def loop(oldInd: Int, newInd: Int, res: Vector[Point]): Vector[Point] = {
      if (newInd >= len) res
      else loop(oldInd + 1, newInd + 1, res :+ circle(oldInd))
    }
    loop(ind, 0, Vector())
  }
}

object DynamicConvexHull {
  type PointsWithConvexHull = (Vector[Point], Vector[Point])
}

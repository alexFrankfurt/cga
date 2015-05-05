package com.alex.cga
package animation

import java.awt.Graphics2D

import geometry.plain.{ConvexPolygon, Point}
import collection.mutable
import sugar.Extensions.{draw => drawFigure}
import Container.randPoint
import algorithm.PlainFigureRelation._
import algorithm.{binaryTest, pointToLine}
import Point._

class DynamicConvexHull { self: Animatable with MakeAnimation with DrawGraphics2DAnimation =>
  import DynamicConvexHull.PointsWithConvexHull
  type DynamicState = PointsWithConvexHull

  val initPoint = randPoint(200)
  val delay = 100
  val initialState = (mutable.ListBuffer[Point](initPoint), mutable.ListBuffer[Point](initPoint))

  def draw(ds: DynamicState)(implicit g: Graphics2D) = {
    val (pts, ch) = ds

    drawFigure(pts)
    drawFigure(ConvexPolygon(ch: _*))
  }

  def findNewState(ind: Int) = {
    val (pts, ch) = animation(ind)
    val np = randPoint(200)

    if (pts.length == 1) {
      (pts += np, ch += np)
    }
    else if (pts.length == 2){
      (pts += np, ch += np)
    }
    else {
      val chp = ConvexPolygon(ch: _*)
      (pts += np, np R chp match {
        case In => ch
        case Out => updateCh(np, ch)
      })
    }
  }

  def updateCh(np: Point, ch: mutable.ListBuffer[Point]) = {
    val chp = ConvexPolygon(ch: _*)
    val ins = (for {
      (side, i) <- chp.sides.view.zipWithIndex
      if (np R side.toLine) == Righter
    } yield i).force

    val resl = ch.take(ins.head)
    val resr = ch.takeRight(ch.length - ins.last)

    (resl :+ np) ++ resr
  }
}

object DynamicConvexHull {
  type PointsWithConvexHull = (mutable.ListBuffer[Point], mutable.ListBuffer[Point])
}

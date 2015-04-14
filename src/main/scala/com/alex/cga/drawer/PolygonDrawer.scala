package com.alex.cga
package drawer

import java.awt.geom.{Line2D, Ellipse2D}
import java.awt.{Color, Graphics2D}
import java.util.logging.Level

import figure.PlainPolygon
import figure.plain.Segment
import algorithm.SimpleRelationResolvers.SegmentIntersectPolygon
import Container._

import scala.collection.mutable
import scala.swing.Panel

class PolygonDrawer(polygon: PlainPolygon) extends Panel {
  val xy0 = 200

  var stack = 0
  var points = pts
  override def paintComponent(g: Graphics2D) = drawPolygon(g)

  def drawPolygon(implicit g: Graphics2D) = {
    if (stack == 0) {
      g.setColor(Color.BLACK)
      for (i <- 0 to polygon.num) {
        val pi = polygon.vertices(i)
        val `pi+1` = polygon.vertices(i + 1)
        val line = new Line2D.Double(500 + pi.x, 500 + pi.y, 500 + `pi+1`.x, 500 + `pi+1`.y)
        g.draw(line)
      }
      draw(points)
      stack = 1
      logger.log(Level.INFO, s"in stack = 0: $points")
    } else {
      moveDraw(points)
      logger.log(Level.INFO, s"in stack = 1: $points")
    }
  }

  def draw(points: mutable.MutableList[ActivePoint])(implicit g: Graphics2D) = {
    for (i <- 0 until points.length) {
      val api = points(i)
      val pi = api._1
      g draw {
        new Ellipse2D.Double(xy0 + pi.x, xy0 + pi.y, 3, 3)
      }
    }
  }

  def moveDraw(points: mutable.MutableList[ActivePoint])(implicit g: Graphics2D) = {
    val pointList = points map { ap => ap._1}
    val movedPoints = points map { ap => ap._1 + ap._2 }

    for (i <- 0 until pointList.length) {
      val pi = pointList(i)
      val mpi = movedPoints(i)
      if (Segment(pi, mpi) intersect polygon) {
        edit(pointList(i))
      }
    }
  }
}

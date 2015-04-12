package com.alex.cga
package drawer

import java.awt.geom.{Line2D, Ellipse2D}
import java.awt.{Color, Graphics2D}

import figure.PlainPolygon
import figure.plain.Point
import Container._

import scala.swing.Panel

class PolygonDrawer(polygon: PlainPolygon) extends Panel {
  val xy0 = 200

  var stack = 0
  val points = pList
  override def paintComponent(g: Graphics2D) = drawPolygon(g)

  def drawPolygon(implicit g: Graphics2D) = {
    if (stack == 0) {
      g.setColor(Color.BLACK)
      for (i <- 0 until points.length) {
        val pi = points(i)
        g draw {
          new Ellipse2D.Double(xy0 + pi.x, xy0 + pi.y, 1, 1)
        }
      }
      for (i <- 0 to polygon.num) {
        val pi = polygon.vertices(i)
        val `pi+1` = polygon.vertices(i + 1)
        val line = new Line2D.Double(500 + pi.x, 500 + pi.y, 500 + `pi+1`.x, 500 + `pi+1`.y)
        g.draw(line)
      }
      draw (points)
    } else {
      draw (points.moved)
    }
  }

  def draw(points: List[Point])(implicit g: Graphics2D) = {

  }
}

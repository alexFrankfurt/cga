package com.alex.cga
package drawer

import java.awt.geom.{Line2D, Ellipse2D}
import java.awt.{Color, Graphics2D}

import figure.PlainPolygon

import scala.swing.Panel

class PolygonDrawer(polygon: PlainPolygon) extends Panel{
  override def paintComponent(g: Graphics2D) = drawPolygon(g)

  def drawPolygon(g: Graphics2D) = {
    g.setColor(Color.BLACK)
    for (i <- 0 to polygon.num) {
      val pi = polygon.vertices(i)
      val `pi+1` = polygon.vertices(i + 1)
      val line = new Line2D.Double(300 + pi.x, 300 + pi.y, 300 + `pi+1`.x,300 + `pi+1`.y)
      g.draw(line)
    }
  }
}

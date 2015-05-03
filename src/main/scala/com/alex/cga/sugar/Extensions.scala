package com.alex.cga
package sugar

import com.alex.cga.sugar.SquareBound

import swing.Graphics2D
import java.awt.geom.{Line2D, Ellipse2D}
import com.alex.cga.animation.CoordinateCenter
import geometry.plain.{Point, Polygon}

object Extensions {
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

  def draw(pts: Seq[Point])(implicit g: Graphics2D, cs: CoordinateCenter) = {
    for (i <- 0 until pts.length) {
      val pi = pts(i)
      g draw {
        new Ellipse2D.Double(cs.y + pi.y, cs.x + pi.x, 3, 3)
      }
    }
  }

  def draw(pol: Polygon)(implicit g: Graphics2D, cs: CoordinateCenter) = {
    for (i <- 0 to pol.num) {
      val pi = pol.vertices(i)
      val `pi+1` = pol.vertices(i + 1)
      g.draw(new Line2D.Double(cs.x + pi.y, cs.x + pi.x, cs.y + `pi+1`.y, cs.y + `pi+1`.x))
    }
  }

  implicit class PolygonBoundSquare(pol: Polygon) {
    def boundSquare: SquareBound = {
      val pl = pol.vertices.list
      SquareBound(pl.xMin, pl.xMax, pl.yMin, pl.yMax)
    }
  }
}

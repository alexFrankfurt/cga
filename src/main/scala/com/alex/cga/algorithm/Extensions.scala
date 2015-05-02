package com.alex.cga.algorithm

import com.alex.cga.geometry.plain.{Polygon, Point}

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

  implicit class PolygonBoundSquare(pol: Polygon) {
    def boundSquare: SquareBound = {
      val pl = pol.vertices.list
      SquareBound(pl.xMin, pl.xMax, pl.yMin, pl.yMax)
    }
  }
}

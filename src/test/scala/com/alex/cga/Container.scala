package com.alex.cga

import com.alex.cga.geometry.plain.{Segment, ConvexPolygon, Point}

object Container {
  val p1 = Point(0, -1)
  val p2 = Point(1, 0)
  val p3 = Point(0, 1)
  val p4 = Point(-1, 0)

  val cp = ConvexPolygon(p1, p2, p3, p4)

  val seg = Segment(Point(1 / 2, -1 / 2), Point(3, -3))
}

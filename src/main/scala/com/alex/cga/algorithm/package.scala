package com.alex.cga

import figure.plain.{Segment, ConvexPolygon, Point}

package object algorithm {
  implicit val binaryTest = (figure1: Point, figure2: ConvexPolygon) => {
    import Point._
    val a = Point(1,2)
    On
  }

  implicit val segPolTest = (figure1: Segment, figure2: ConvexPolygon) => {
    import Segment._
    On
  }
}

package com.alex.cga.geometry.plain

import com.alex.cga.Circle

abstract class Polygon(val vertices: Circle[Point]) extends Figure {
  val num = vertices.length
}

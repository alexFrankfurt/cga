package com.alex.cga
package geometry
package plain


class ConcavePolygon(vertices: Circle[Point]) extends Polygon(vertices: Circle[Point]) {

}

object ConcavePolygon {
  def apply(vertices: Point*) = new ConcavePolygon(Circle(vertices))
}


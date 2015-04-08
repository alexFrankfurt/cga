package com.alex.cga
package figure
package plain


class ConcavePolygon(vertices: Circle[Point]) extends PlainPolygon(vertices: Circle[Point]) {

}

object ConcavePolygon {
  def apply(vertices: Point*) = new ConcavePolygon(Circle(vertices))
}


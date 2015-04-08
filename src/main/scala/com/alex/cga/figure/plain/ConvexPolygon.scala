package com.alex.cga
package figure
package plain



class ConvexPolygon(vertices: Circle[Point]) extends PlainPolygon(vertices: Circle[Point]) {


}

object ConvexPolygon {
  def apply(vertices: Point*) = new ConvexPolygon(Circle(vertices))
}


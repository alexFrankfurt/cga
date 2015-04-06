package com.alex.cga
package figure
package plain



class ConvexPolygon(vertices: Circle[Point]) extends PlainPolygon {


}

object ConvexPolygon {
  def apply(vertices: Point*) = new ConvexPolygon(Circle(vertices))
}


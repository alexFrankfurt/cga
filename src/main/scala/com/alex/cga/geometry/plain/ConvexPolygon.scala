package com.alex.cga
package geometry
package plain



class ConvexPolygon(vertices: Circle[Point]) extends Polygon(vertices: Circle[Point]) {


}

object ConvexPolygon {
  def apply(vertices: Point*) = new ConvexPolygon(Circle(vertices))
}


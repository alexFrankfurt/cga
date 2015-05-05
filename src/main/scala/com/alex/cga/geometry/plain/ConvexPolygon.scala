package com.alex.cga
package geometry
package plain



class ConvexPolygon(vertices: Circle[Point]) extends Polygon(vertices: Circle[Point]) {
  def sides = {
    val res = for {
      (vertex1, vertex2) <- vertices.view zip vertices.view.tail
    } yield Segment(vertex1, vertex2)
    res.force :+ Segment(vertices.last, vertices.head)
  }

  def view = vertices.view
}

object ConvexPolygon {
  def apply(vertices: Point*) = new ConvexPolygon(Circle(vertices))
}


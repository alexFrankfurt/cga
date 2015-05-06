package com.alex.cga

import com.alex.cga.geometry.plain.{Point, ConvexPolygon}


object Main {
  def main(args: Array[String]) {
    val p1 = Point(1, 3)
    val p2 = Point(0, 2)
    val p3 = Point(-19, 10)
//    val a = List(p1, p2, p3)
//    val seg1 = Segment(p1, p2)
//    val seg2 = Segment(p3, p2)
    val pol2 = ConvexPolygon(p3, p2, p1)
    val a = Vector(1, 2, 3)
//    val res = for {
//      (el, i) <- a.view zip a.tail
//    } yield (el, i)
//    println(res.force)
//
//    println(Seq(1, 2) :+ 8)
    val b = Vector(8, 9, 10)
    val c = 4

    println((a :+ c) ++ b)
    println(pol2.sides)
  }
}

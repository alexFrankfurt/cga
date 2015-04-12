package com.alex.cga

import figure._
import Angle._
import plain._
import algorithm.PlainFigureRelation._
import algorithm._
import language.postfixOps

object Main {
  def main(args: Array[String]) {
//    val p1 = Point(1, 3)
//    val p2 = Point(0, 2)
//    val p3 = Point(-19, 10)
//    val seg1 = Segment(p1, p2)
//    val seg2 = Segment(p3, p2)
//    val pol2 = ConvexPolygon(p3, p2, p1)
//    println(Point(0, 3) R pol2)
//    println(Angle(20.0) == Angle(20.0, Degrees))
//    val tpe = new FreeVector(Point(0, 0), Point(4, 3))
//    println(tpe.x, tpe.y)
//    val list = Vector(4.2, 8.8, 1.2, 2.2, 3.2)
//    println(list./:(list.head)((x, y) => if (x > y) x else y))
//    println(List(1,2,3).sum)



    val p1 = Point(2, 0)
    val p2 = Point(2, 2)
    val p3 = Point(0, 2)
    val p4 = Point(0, 0)
    val concavePolygon = ConcavePolygon(p1, p2, p3, p4)

    val pt = Point(1, 1)
    val rel = pt R concavePolygon
    println(rel)
  }
}


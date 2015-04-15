package com.alex.cga

import java.util.logging.{Level, Logger}

import algorithm.PlainFigureRelation._
import algorithm._
import figure.Direction
import figure.plain._

import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.Random

object Container {
//  type ActivePoint = (Point, DoubleVector)

  val logger = Logger.getLogger(Container.getClass.getName)

  val p1 = Point(5, 5)
  val p2 = Point(-8, -10)
  val p3 = Point(0, -3)
  val p4 = Point(10, -11)
  val p5 = Point(15, 10)
  val innerPolygon = ConcavePolygon(p1, p2, p3, p4, p5)

  val op1 = Point(30, 40)
  val op2 = Point(0, 50)
  val op3 = Point(-30, 40)
  val op4 = Point(-30, -40)
  val op5 = Point(0, -50)
  val op6 = Point(30, -40)
  val outerPolygon = ConvexPolygon(op1, op2, op3, op4, op5, op6)

  val d1 = Direction(1.1, 2.2)
  val d2 = Direction(-1.2, 0.2)
  val d3 = Direction(0.3, 0.8)

  val (fp1, fp2) = (Point(5.3, 5.8), Point(4, 7.3))
  val (pd1, pd2) = (Direction(0.03, 0.02), Direction(-0.04, 0.02))
  val (fp3, fp4) = (Point(1.1, -6), Point(3.8, 10))
  val (pd3, pd4) = (Direction(-0.03, 0.02), Direction(0.03, 0.01))

  var points = List(fp1, fp2, fp3, fp4)
  var directions = List(pd1, pd2, pd3, pd4)

//  var points = pts(5)
//  var directions = drs(5)

//  val pts: mutable.MutableList[ActivePoint] =
//    mutable.MutableList((p1, d1), (p2, d2), (p3, d3))

  def pts(n: Int): List[Point] = {
    @tailrec
    def loop(c: Int, list: List[Point]): List[Point] = {
      if (c >= n) list
      else {
        val a = Point(Random.nextInt(60) - 30, Random.nextInt(60) - 30)
        if ((a R outerPolygon) == Point.In) loop(c + 1, a :: list)
        else loop(c, list)
      }
    }
    loop(0, List())
  }

  def drs(n: Int): List[Direction] = {
    @tailrec
    def loop(c: Int, list: List[Direction]): List[Direction] = {
      if (c >= n) list
      else {
        val a = Direction(Random.nextInt(100) / 1000, Random.nextInt(100) / 1000)
        loop(c + 1, a :: list)
      }
    }
    loop(0, List())
  }
}

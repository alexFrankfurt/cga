package com.alex.cga

import java.util.logging.{Level, Logger}

import algorithm.PlainFigureRelation._
import algorithm._
import com.alex.cga.animation.CoordinateCenter
import geometry.Direction
import geometry.plain._

import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.Random

object Container {
//  type ActivePoint = (Point, DoubleVector)

  val logger = Logger.getLogger(Container.getClass.getName)

  val cs = CoordinateCenter(300, 300)
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

//  var points = List(fp1, fp2, fp3, fp4)
//  var directions = List(pd1, pd2, pd3, pd4)

  val size = 10
  var points = pts(size)
  var directions = drs(size)

//  val pts: mutable.MutableList[ActivePoint] =
//    mutable.MutableList((p1, d1), (p2, d2), (p3, d3))

  def pts(n: Int): List[Point] = {
    @tailrec
    def loop(c: Int, list: List[Point]): List[Point] = {
      if (c >= n) list
      else {
        val a = Point(Random.nextInt(60) - 30, Random.nextInt(60) - 30)
        if ((a R outerPolygon) == Point.Out || (a R innerPolygon) == Point.In) loop(c, list)
        else loop(c + 1, a :: list)
      }
    }
    loop(0, List())
  }

  def drs(n: Int): List[Direction] = {
    @tailrec
    def loop(c: Int, list: List[Direction]): List[Direction] = {
      if (c >= n) list
      else {
        val a = Direction(Random.nextDouble() / 10, Random.nextDouble() / 10)
        loop(c + 1, a :: list)
      }
    }
    loop(0, List())
  }

  /** Generates random point in interval (-n, n).
   *
   *  @param n right border of interval.
   */
  def randPoint(n: Int) = {
    Point(Random.nextDouble() * n * 2 - n, Random.nextDouble() * n * 2 - n)
  }
}

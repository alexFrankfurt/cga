package com.alex.cga
package algorithm

import org.scalatest.{FlatSpec, Matchers}
import math.Pi
import figure.Angle
import figure.plain.Point

class AlgorithmSpec extends FlatSpec with Matchers{
  "algorithm" should "find right angle" in {
    val p1 = Point(1, 0)
    val p2 = Point(0, 0)
    val p3 = Point(0, 1)
    val alpha = Angle(Pi / 2)
    ∠(p1, p2, p3) should be (alpha)
  }
  it should "straight angle" in {
    val p1 = Point(1, 0)
    val p2 = Point(0, 0)
    val p3 = Point(-1, 0)
    val alpha = Angle(Pi)
    ∠(p1, p2, p3) should be (alpha)
  }
  it should "zerro angle" in {
    val p1 = Point(1, 0)
    val p2 = Point(0, 0)
    val p3 = Point(1, 0)
    val alpha = Angle(0.0)
    ∠(p1, p2, p3) should be (alpha)
  }
  it should "Pi / 4 acute angle" in {
    val p1 = Point(1, 0)
    val p2 = Point(0, 0)
    val p3 = Point(1, 1)
    val alpha = Angle(Pi / 4)
    ∠(p1, p2, p3) should be (alpha)
  }
  it should "Pi / 4 * 3 obtuse angle" in {
    val p1 = Point(1, 0)
    val p2 = Point(0, 0)
    val p3 = Point(-1, 1)
    val alpha = Angle(Pi / 4 * 3)
    ∠(p1, p2, p3) should be (alpha)
  }
}

package com.alex.cga.geometry

import org.scalatest.{Matchers, FlatSpec}
import com.alex.cga.geometry.plain.{FreeVector, Point}

class VectorSpec extends FlatSpec with Matchers{
  "Vector" should "be calculated right" in {
    val p1 = Point(0, 0)
    val p2 = Point(1, 1)
    val p3 = Point(1, 2)
    val v1 = FreeVector(p1, p2)
    val v2 = FreeVector(p1, p3)
    v1 * v2 should be (3)
  }
  it should "be equals to passed value" in {
    val p1 = Point(0, 0)
    val p2 = Point(1, 1)
    val v = FreeVector(p1, p2)
    v.x should be(p2.x - p1.x)
  }
}

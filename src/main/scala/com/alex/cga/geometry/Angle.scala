package com.alex.cga
package geometry

import math.Pi

class Angle(val value: Double, val m: Angle#Measure) {
  import Angle._
  type Measure = Value

  def compare(that: Angle) = {
    if (m == that.m) value - that.value
    else if (that.m == Degrees)
      value - that.value * Pi / 180
    else
      value - that.value * 180 / Pi
  }

  def <(that: Angle) = (this compare that) < 0

  def >(that: Angle) = (this compare that) > 0

  override def equals(obj: scala.Any): Boolean = {
    val that = obj.asInstanceOf[Angle]
    if (value - that.value < 2e-16 && m == that.m) true
    else false
  }

  override def toString = "Angle(" + value + ", " + m + ")"
}

object Angle extends Enumeration{
  val Radians, Degrees = Value
  def apply(x: Double) = new Angle(x, Radians)
  def apply(x: Double, m: Value) = new Angle(x, m)
}

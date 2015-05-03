package com.alex.cga.animation

import java.awt.Graphics2D

import com.alex.cga.geometry.plain.Point
import collection.mutable

class DynamicConvexHull { self: Animatable with MakeAnimation with DrawGraphics2DAnimation =>
  import DynamicConvexHull.PointsWithConvexHull
  type DynamicState = PointsWithConvexHull

  var initialState = (mutable.ListBuffer(), mutable.ListBuffer())

  def draw(ds: DynamicState)(implicit g: Graphics2D) = ???

  def findNewState(ind: Int) = ???
}

object DynamicConvexHull {
  type PointsWithConvexHull = (mutable.ListBuffer[Point], mutable.ListBuffer[Point])
}

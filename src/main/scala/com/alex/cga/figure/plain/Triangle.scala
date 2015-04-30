package com.alex.cga.figure.plain

import com.alex.cga.algorithm.||

case class Triangle(p1: Point, p2: Point, p3: Point) {
  def parSquare = ||(p2, p1, p2, p3)
}

package com.alex.cga.animation

import com.alex.cga.geometry.Direction
import com.alex.cga.geometry.plain.Point
import language.implicitConversions

case class DynamicImage(points: List[Point] = List(), directions: List[Direction] = List()){
}

object DynamicImage {
  implicit def DynamicImageToPtsDrs(di: DynamicImage): (List[Direction], List[Point]) = (di.directions, di.points)
  implicit def DynamicImageToList(di: DynamicImage): List[Point] = di.points
}
package com.alex.cga.animation

import java.awt.Graphics2D

import com.alex.cga.Container

class Visualization(
    val staticState: StaticImage,
    var animation: List[DynamicImage])
    (implicit coordinateCenter: CoordinateCenter)
  extends Animatable[StaticImage, DynamicImage]
  with Sheeps{
  val cs = coordinateCenter
  var lastState = 0
  var currentCapture = 0
  val innerPolygon = staticState.concavePolygon
  val outerPolygon = staticState.convexPolygon

  def draw()(implicit g: Graphics2D): Unit = {
    drawAll(animation(currentCapture))
    currentCapture += 1
  }

  def next(): Unit = {
    val cur = animation(lastState)
    val res = findNext(cur.directions, cur.points)
    animation = DynamicImage(res._1, res._2) :: animation
  }

  def reverse(): Unit = {
    animation = animation.reverse
    Container.logger.info("animation reversed!")
  }
}

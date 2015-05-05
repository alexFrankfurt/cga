package com.alex.cga.animation

import com.alex.cga.Container

import swing.Graphics2D

trait DrawGraphics2DAnimation { self: Animatable =>
  implicit val cs: CoordinateCenter = Container.cs
  val delay: Int

  def draw(ds: DynamicState)(implicit g: Graphics2D): Unit

  def draw(nth: Int)(implicit g: Graphics2D): Unit =
    draw(animation(nth))
}

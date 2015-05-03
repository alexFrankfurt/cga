package com.alex.cga.animation

import swing.Graphics2D

trait DrawGraphics2DAnimation { self: Animatable =>
  def draw(ds: DynamicState)(implicit g: Graphics2D): Unit

  def draw(nth: Int)(implicit g: Graphics2D): Unit =
    draw(animation(nth))
}

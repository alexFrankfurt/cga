package com.alex.cga.animation

import java.awt.Graphics2D

trait Animatable[A, B] {
  type StaticState = A
  type DynamicState = B
  type Animation = List[DynamicState]

  val staticState: StaticState
  var animation: Animation

  def draw()(implicit g: Graphics2D): Unit
}

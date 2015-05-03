package com.alex.cga.animation

import collection.mutable

trait Animatable {
  type DynamicState
  type Animation = mutable.ListBuffer[DynamicState]

  val animation: Animation
}

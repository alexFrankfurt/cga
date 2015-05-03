package com.alex.cga.animation

import collection.mutable

trait MakeAnimation { self: Animatable =>
  val initialState: DynamicState
  val animation = mutable.ListBuffer(initialState)
  var indOfLastState = animation.length - 1

  def findNewState(ind: Int): DynamicState

  def appendNewState(): Unit = {
    animation += findNewState(indOfLastState)
    indOfLastState += 1
  }
}

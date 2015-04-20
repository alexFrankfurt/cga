package com.alex.cga.animation



trait Animatable {
  type State
  type Animation = IndexedSeq[State]
}

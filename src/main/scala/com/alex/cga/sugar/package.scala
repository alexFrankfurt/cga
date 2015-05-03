package com.alex.cga



package object sugar {
  implicit class UnitState(u: Unit) {
    def state = ()
  }
}

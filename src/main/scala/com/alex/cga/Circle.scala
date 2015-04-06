package com.alex.cga

import math.abs

object Circle {
  def apply[B](lst: Seq[B]) = new Circle(lst.toList, lst.length)
}

case class Circle[+A](list: List[A], length: Int) {

  def apply(ind: Int) = {
    if (ind >= 0) list(ind % length)
    else list(length - abs(ind) % length)
  }

  def foreach[U](f: A => U) = list.foreach(f)
}

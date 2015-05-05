package com.alex.cga

import math.abs

case class Circle[+A](list: Vector[A], length: Int) {

  def apply(ind: Int) = {
    if (ind >= 0) list(ind % length)
    else list(length - abs(ind) % length)
  }

  def last = list(length - 1)

  def head = list.head

  def foreach[U](f: A => U) = list.foreach(f)

  def view = list.view

  def tail = list.tail
}

object Circle {
  def apply[B](lst: Seq[B]) = new Circle(lst.toVector, lst.length)
}

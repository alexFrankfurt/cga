package com.alex.cga
package animation

import akka.actor.{Actor, Props}
import swing.SimpleSwingApplication

import Container._

class Maker extends Actor {
  import Maker._

  implicit val cs = CoordinateCenter(300, 300)

  val visualization =
    new Visualization(StaticImage(outerPolygon,innerPolygon), List(DynamicImage(points, directions)))
  var frame: SimpleSwingApplication = _

  def receive = {
    case FindNextState =>
      visualization.next()
    case CreateFrame(args) =>
      frame = new AnimatorFrame(visualization)
      frame.startup(args)
    case MakeAnimation(times) =>
      for (i <- 0 until times) visualization.next()
      sender() ! StatusOk
    case Reverse =>
      visualization.reverse()
      sender() ! StatusOk
  }
}

object Maker {
  case object StatusOk
  case object Reverse
  case object FindNextState
  case class CreateFrame(args: Array[String])
  case class MakeAnimation(times: Int)

  def props() = Props(new Maker)
}
package com.alex.cga
package animation

import akka.actor.{Actor, Props}
import swing.SimpleSwingApplication

import Container._

class FrameManager extends Actor {
  import FrameManager._

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
      sender() ! AnimationMade
    case ReverseAnimation =>
      visualization.reverse()
      sender() ! AnimationReady
  }
}

object FrameManager {
  case object AnimationMade
  case object AnimationReady
  case object ReverseAnimation
  case object FindNextState
  case class CreateFrame(args: Array[String])
  case class MakeAnimation(times: Int)

  def props() = Props(new FrameManager)
}
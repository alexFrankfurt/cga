package com.alex.cga
package animation

import akka.actor.{Actor, Props}
import swing.SimpleSwingApplication

class FrameManager extends Actor {
  import FrameManager._
  val visualization = new DynamicConvexHull with Animatable with MakeAnimation with DrawGraphics2DAnimation
  var frame: SimpleSwingApplication = _

  def receive = {
    case FindNextState =>
      visualization.appendNewState()
    case CreateFrame(args) =>
      frame = new AnimatorFrame(visualization)
      frame.startup(args)
    case AppendAnimation(times) =>
      for (i <- 0 until times) visualization.appendNewState()
      sender() ! AnimationAppended

  }
}

object FrameManager {
  case object AnimationAppended

  case object FindNextState
  case class AppendAnimation(times: Int)
  case class CreateFrame(args: Array[String])

  def props() = Props(new FrameManager)
}

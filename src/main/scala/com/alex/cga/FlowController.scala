package com.alex.cga

import akka.actor.{Props, Actor}
import animation.FrameManager
import FrameManager.{props => _, _}

class FlowController(args: Array[String]) extends Actor{
  import FlowController._
  def receive = {
    case Start =>
      val maker = context.actorOf(FrameManager.props(), "maker")
      maker ! AppendAnimation(2000)
    case AnimationAppended =>
      sender() ! CreateFrame(args)
  }
}

object FlowController {
  case object Start

  def props(args: Array[String]) = Props(new FlowController(args))
}

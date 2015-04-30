package com.alex.cga

import akka.actor.ActorSystem
import FlowController.Start

object AnimationBoot extends App {
  implicit val system = ActorSystem()
  system.actorOf(FlowController.props(args)) ! Start
}

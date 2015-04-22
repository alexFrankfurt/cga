package com.alex.cga

import akka.actor.ActorSystem
import animation.Maker
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import com.alex.cga.animation.Maker.{Reverse, CreateFrame, MakeAnimation}

object AnimationBoot extends App {
  implicit val system = ActorSystem()

  val maker = system.actorOf(Maker.props(), "maker")
  maker ! MakeAnimation(2000)
  system.scheduler.scheduleOnce(4.seconds){
    maker ! Reverse
  }
  system.scheduler.scheduleOnce(5.seconds){
    maker ! CreateFrame(args)
  }
}

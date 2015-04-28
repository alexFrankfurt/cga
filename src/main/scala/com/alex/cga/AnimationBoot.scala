package com.alex.cga

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import animation.Maker
import scala.concurrent.Await
import scala.concurrent.duration._

import com.alex.cga.animation.Maker.{Reverse, CreateFrame, MakeAnimation}

object AnimationBoot extends App {
  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5.seconds)
  val maker = system.actorOf(Maker.props(), "maker")

  val future = maker ? MakeAnimation(2000)
  val res1 = Await.result(future, 5.seconds)
  val res2 = Await.result(maker ? Reverse, 5.seconds)
  maker ! CreateFrame(args)
}

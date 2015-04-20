package com.alex.cga.drawer

import akka.actor.{ActorLogging, Actor}

import scala.swing.Publisher
import scala.swing.event.Event

case class ActorEvent() extends Event

class Drawer(n: String) extends Actor with Publisher with ActorLogging{
  def receive = {
    case "ref" =>
      sender ! this
    case _ =>
      println("Hello")
      publish(new ActorEvent)
  }
}
package com.alex.cga

import akka.actor.{Deploy, Props, ActorSystem, Actor}
import akka.actor.Actor.Receive
import akka.util.Timeout
import com.alex.cga.drawer.Drawer
import figure._
import Angle._
import plain._
import algorithm.PlainFigureRelation._
import algorithm._
import language.postfixOps
import scala.annotation.tailrec
import scala.concurrent.Await
import scala.swing.Publisher
import scala.swing.event.Event

object Main {
  def main(args: Array[String]) {
//    val p1 = Point(1, 3)
//    val p2 = Point(0, 2)
//    val p3 = Point(-19, 10)
//    val seg1 = Segment(p1, p2)
//    val seg2 = Segment(p3, p2)
//    val pol2 = ConvexPolygon(p3, p2, p1)
//    println(Point(0, 3) R pol2)
//    println(Angle(20.0) == Angle(20.0, Degrees))
//    val tpe = new FreeVector(Point(0, 0), Point(4, 3))
//    println(tpe.x, tpe.y)
//    val list = Vector(4.2, 8.8, 1.2, 2.2, 3.2)
//    println(list./:(list.head)((x, y) => if (x > y) x else y))
//    println(List(1,2,3).sum)



//    val p1 = Point(2, 0)
//    val p2 = Point(2, 2)
//    val p3 = Point(0, 2)
//    val p4 = Point(0, 0)
//    val concavePolygon = ConcavePolygon(p1, p2, p3, p4)
//
//    val pt1 = Point(1, 1)
//    val pt2 = Point(1 / 2, 1 / 2)
//    val rel1 = pt1 R concavePolygon
//    val rel2 = pt2 R concavePolygon
//    println(rel1 + " : " + rel2)
//
//    val p1 = Point(-29.9, -33.3)
//    val p2 = Point(-30.9, -34.3)
//    val iseg = Segment(p1, p2)
//    import Container.outerPolygon
//    import SimpleRelationResolvers._
////    val op5 = Point(0, -50)
////    val op6 = Point(30, -40)
////    val seg = Segment(op5, op6)
////    val a = iseg R seg
//
//    val a = Segment(p1, p2) intersect outerPolygon orNull
//    val b = Segment(Point(-30.0, 40.0), Point(-30.0, -40.0))
//    val res = iseg hit b
//    println(res)
//    val st = compat.Platform.currentTime
//    for (i <- 0 to 20){
//      println(p1 R outerPolygon)
//      println(p2 R outerPolygon)
//    }
//
//    val et = compat.Platform.currentTime
//
//    println(et - st)

//    Old direction: Direction(0.8942794458955383,0.6679609060595411)
//    New Direction: Direction(0.4416423662235438,0.6679609060595411)

//    import Container._
//    import SimpleRelationResolvers._
//
//    val s = Segment(op4, op6)
//    val a = Segment(Point(0, 0), Point(0.8942794458955383,0.6679609060595411))
//
//    val b = a hit s
//    println(b)

//    val a = List(1, 2, 3, 4)
//    val b = a.reverseIterator
//
//    while (b.hasNext) println(b.next())
//    import akka.pattern.ask
//    import concurrent.duration._
//    import concurrent.ExecutionContext.Implicits.global
//    import drawer.ActorEvent
//
//    implicit val timeout = Timeout(5.seconds)
//
//    implicit val system = ActorSystem("animation-system")
//    val b = system.actorOf(Props(new Drawer("jfkdl")), "pub")
//    val a = new AnimatorFrame
//
//    val f = b ? "ref"
//    val r = Await.result(f, 5.seconds).asInstanceOf[Drawer]
//
//    println(r.publish(new ActorEvent))
//
//    a.top.listenTo(r)
//    a.startup(args)
//    b ! "Hello"
//    r.publish(new ActorEvent)
//    system.scheduler.schedule(100 milli, 500 milli, b, "some")
  }
}


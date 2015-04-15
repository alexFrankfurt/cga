package com.alex.cga
package drawer

import java.awt.geom.{Line2D, Ellipse2D}
import java.awt.{Color, Graphics2D}
import java.util.logging.Level

import Container._
import algorithm._
import algorithm.PlainFigureRelation._
import algorithm.SimpleRelationResolvers._
import com.alex.cga.figure.{PlainPolygon, Direction}
import figure.plain.{Point, Segment}
import Point._

import scala.annotation.tailrec
import swing.Panel

class PictureDrawer extends Panel {
  val xy0 = 200
  var stack = 0

  override def paintComponent(g: Graphics2D) = drawPicture(g)

  def drawPicture(implicit g: Graphics2D) = {
    if (stack == 0) {
      stack = 1
      
      g.setColor(Color.BLACK)
      drawPolygons
      draw(points)
      
//      logger.log(Level.INFO, s"in stack = 0:\nPoints: $points\nDirections: $directions")
    } else {
      drawPolygons
      moveDrawPoints
//      logger.log(Level.INFO, s"in stack = 1:\nPoints: $points\nDirections: $directions")
    }
  }

  def drawPolygons(implicit g: Graphics2D): Unit = {
    drawPolygon(innerPolygon)
    drawPolygon(outerPolygon)
  }

  def drawPolygon(pol: PlainPolygon)(implicit g: Graphics2D): Unit = {
    for (i <- 0 to pol.num) {
      val pi = pol.vertices(i)
      val `pi+1` = pol.vertices(i + 1)
      g.draw(new Line2D.Double(xy0 + pi.x, xy0 + pi.y, xy0 + `pi+1`.x, xy0 + `pi+1`.y))
    }
  }

  def draw(points: List[Point])(implicit g: Graphics2D): Unit = {
    for (i <- 0 until points.length) {
      val pi = points(i)
      g draw {
        new Ellipse2D.Double(xy0 + pi.x, xy0 + pi.y, 3, 3)
      }
    }
  }

  def moveDrawPoints(implicit g: Graphics2D): Unit = {
    @tailrec
    def loop
    (pts: List[Point], drs: List[Direction], npts: List[Point], ndrs: List[Direction]):
    (List[Point], List[Direction]) = {
      if (pts.isEmpty) (npts, ndrs)
      else {
        val oldP :: oldPts = pts
        val oldD :: oldDrs = drs
        val newP = oldP + oldD
        val pVector = Segment(oldP, newP)
        val rel = (newP R innerPolygon, newP R outerPolygon)
        g.setColor(Color.RED)
        g.draw(new Line2D.Double(xy0 + oldP.x, xy0 + oldP.y, xy0 + newP.x, xy0 + newP.y))

        rel match {
          case (Out, In) => loop(oldPts, oldDrs, newP :: npts, oldD :: ndrs)
          case (In, _) => loop(oldPts, oldDrs, npts, ndrs)
          case (_, Out) =>
            val intersectedSegment = (pVector intersect outerPolygon).orNull
            val newD = pVector hit intersectedSegment
//            logger.log(Level.INFO, "Got intersection: " + oldP + " : " + newP + "\nNew Direction: " + newD)
//            logger.log(Level.INFO, intersectedSegment.toString)
            loop(oldPts, oldDrs, oldP :: npts, newD :: ndrs)
        }
      }
    }
    
    val n = loop(points, directions, List(), List())
    points = n._1
    directions = n._2
//    logger.info(s"Points: $points\nDirections: $directions")
    draw(points)
  }
}

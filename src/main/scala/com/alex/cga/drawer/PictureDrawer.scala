package com.alex.cga
package drawer

import java.awt.geom.{Line2D, Ellipse2D}
import java.awt.{Color, Graphics2D}
import java.util.logging.Level

import Container._
import algorithm._
import algorithm.PlainFigureRelation._
import algorithm.SimpleRelationResolvers._
import com.alex.cga.animation.Visualization
import com.alex.cga.figure.{PlainPolygon, Direction}
import figure.plain.{Point, Segment}
import Point._

import scala.annotation.tailrec
import swing.Panel

class PictureDrawer(visualization: Visualization) extends Panel {
  val xy0 = 200
  var stack = 0

  override def paintComponent(g: Graphics2D) = drawPicture(g)

  def drawPicture(implicit g: Graphics2D) = {
    visualization.draw()
//    if (stack == 0) {
//      stack = 1
//
//      g.setColor(Color.BLACK)
//      drawPolygons
//      draw(points)
//
//      logger.log(Level.INFO, s"in stack = 0:\nPoints: $points\nDirections: $directions")
//    } else {
//      drawPolygons
//      moveDrawPoints
//      logger.log(Level.INFO, s"in stack = 1:\nPoints: $points\nDirections: $directions")
//    }
  }

}

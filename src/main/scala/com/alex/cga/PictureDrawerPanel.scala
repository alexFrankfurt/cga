package com.alex.cga

import java.awt.Graphics2D

import animation._
import sugar.UnitState
import language.postfixOps

import scala.swing.Panel

class PictureDrawerPanel(visualization: Animatable with DrawGraphics2DAnimation) extends Panel {
  var nth = 0
  override def paintComponent(g: Graphics2D) = {
    implicit val gi = g
    visualization draw nth state

    nth += 1
  }
}

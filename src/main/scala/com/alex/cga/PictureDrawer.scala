package com.alex.cga

import java.awt.Graphics2D

import com.alex.cga.animation.Visualization

import scala.swing.Panel

class PictureDrawer(visualization: Visualization) extends Panel {
  override def paintComponent(g: Graphics2D) = visualization.draw()(g)
}

package com.alex.cga

import drawer._
import Container.pol

import swing._

object Animator extends SimpleSwingApplication {
  val top = new MainFrame {
    size = new Dimension(600, 600)
    title = "Swing"

    val polygon = new PolygonDrawer(pol)

    contents = new BoxPanel(Orientation.Horizontal) {
      preferredSize = new Dimension(600, 600)
      contents += polygon

      border = Swing.EmptyBorder(10, 20, 10, 20)
    }
  }
}

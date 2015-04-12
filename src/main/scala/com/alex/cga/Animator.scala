package com.alex.cga

import java.awt.event.ActionEvent
import javax.swing.Timer

import drawer._
import Container.pol

import swing._

object Animator extends SimpleSwingApplication {
  val top = new MainFrame {
    size = new Dimension(600, 600)
    title = "Swing"

    val timer = new Timer(500, Swing.ActionListener {e => work(e)})
    timer.start()

    val polygon = new PolygonDrawer(pol)

    def work(e: ActionEvent) = {
      peer.repaint()
      polygon.repaint()
    }


    contents = new BoxPanel(Orientation.Horizontal) {
      preferredSize = new Dimension(600, 600)
      contents += polygon

      border = Swing.EmptyBorder(10, 20, 10, 20)
    }
  }
}
package com.alex.cga

import java.awt.event.ActionEvent
import javax.swing.Timer

import drawer._
import Container._

import swing._
import java.util.logging.{Level, Logger}

object Animator extends SimpleSwingApplication {
  val top = new MainFrame {
    size = new Dimension(600, 600)
    title = "Swing"

    val timer = new Timer(1000, Swing.ActionListener {e => work(e)})
    timer.start()

    val image = new PolygonDrawer(pol)

    def work(e: ActionEvent) = {
      logger.log(Level.INFO, "action")
      peer.repaint()
      image.repaint()
    }


    contents = new BoxPanel(Orientation.Horizontal) {
      preferredSize = new Dimension(600, 600)
      contents += image

      border = Swing.EmptyBorder(10, 20, 10, 20)
    }
  }
}

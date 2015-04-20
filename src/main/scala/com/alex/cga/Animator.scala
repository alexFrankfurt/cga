package com.alex.cga

import java.awt.event.ActionEvent
import javax.swing.Timer

import drawer._
import Container._

import scala.swing.event.Event
import swing._
import java.util.logging.{Level, Logger}

class Animator extends SimpleSwingApplication {
  val top = new MainFrame {
    size = new Dimension(600, 600)
    title = "Swing"

    val image = new PictureDrawer

    reactions += {
      case ActorEvent() =>
        logger.info("got event from actor")
        peer.repaint()
        image.repaint()
    }

    def work(e: ActionEvent) = {
    }

//    val timer = new Timer(60, Swing.ActionListener {e => work(e)})
//    timer.start()

    contents = new BoxPanel(Orientation.Horizontal) {
      preferredSize = new Dimension(600, 600)
      contents += image

      border = Swing.EmptyBorder(10, 20, 10, 20)
    }
  }
}

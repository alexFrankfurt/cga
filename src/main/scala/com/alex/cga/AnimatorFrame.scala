package com.alex.cga

import java.awt.event.ActionEvent
import javax.swing.Timer

import animation.Visualization

import scala.swing._

class AnimatorFrame(visualization: Visualization) extends SimpleSwingApplication {
  val top = new MainFrame {
    val image = new PictureDrawer(visualization)
    title = "Swing"

    def work(e: ActionEvent) = {
      peer.repaint()
      image.repaint()
    }

    val timer = new Timer(4, Swing.ActionListener {e => work(e)})
    timer.start()

    contents = new BoxPanel(Orientation.Horizontal) {
      preferredSize = new Dimension(600, 600)
      contents += image

      border = Swing.EmptyBorder(10, 10, 10, 10)
    }
  }
}

package com.alex.cga

import java.awt.event.ActionEvent
import javax.swing.Timer

import com.alex.cga.Container._
import com.alex.cga.animation.Visualization
import com.alex.cga.drawer._

import scala.swing._

class AnimatorFrame(visualization: Visualization) extends SimpleSwingApplication {
  val top = new MainFrame {
    size = new Dimension(600, 600)
    title = "Swing"

    val image = new PictureDrawer(visualization)

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

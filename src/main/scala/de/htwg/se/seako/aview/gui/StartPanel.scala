package de.htwg.se.seako.aview.gui

import de.htwg.se.seako.controller.{Controller, StartGame}
import javax.swing.BorderFactory

import scala.swing.{Button, Dimension, FlowPanel, Frame}

class StartPanel(controller: Controller) extends Frame{

  listenTo(controller)

  title = "Seako"
  background  = java.awt.Color.BLACK
  preferredSize = new Dimension(800, 800)
  centerOnScreen()
  visible = true

  val startButton: Button = new Button("Start Game") {
    background = java.awt.Color.WHITE
    foreground = java.awt.Color.BLACK
    focusable = false
    border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
    visible = true
  }

  contents = new FlowPanel() {
    contents += startButton

  }

  reactions += {
    case event: StartGame =>
  }
}

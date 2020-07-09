package de.htwg.se.seako.aview.gui


import de.htwg.se.seako.controller.Controller

import scala.swing._


class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)

  title = "Seako"
  visible = true
  preferredSize = new Dimension(1200, 900)
  centerOnScreen()
  background = java.awt.Color.BLACK

  
}

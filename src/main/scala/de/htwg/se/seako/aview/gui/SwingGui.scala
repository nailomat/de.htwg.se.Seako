package de.htwg.se.seako.aview.gui


import de.htwg.se.seako.controller.{Controller, StartGame}
import javax.swing.BorderFactory

import scala.swing._


class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)

  title = "Seako"
  background  = java.awt.Color.BLACK
  preferredSize = new Dimension(800, 800)
  centerOnScreen()
  visible = true

  var cells = Array.ofDim[CellPanel](controller.gridSize, controller.gridSize)
  def controlPanel: BorderPanel = new BorderPanel {

  }

  def gridPanel: GridPanel = new GridPanel(controller.grid.size, controller.grid.size) {
    background = java.awt.Color.WHITE
    for {
      row <- 0 until controller.grid.size
      col <- 0 until controller.grid.size
    } {
      val cellPanel = new CellPanel(row, col, controller)
      contents += cellPanel

    }
  }

  val startButton: Button = new Button("Start Game") {
    background = java.awt.Color.WHITE
    foreground = java.awt.Color.BLACK
    focusable = false
    border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
    visible = true
  }

  val cellPanel: CellPanel = new CellPanel(0, 0, controller)


  contents = new BorderPanel {
//    add(startButton, BorderPanel.Position.Center )
//    add(gridPanel, BorderPanel.Position.Center)
    add(cellPanel, BorderPanel.Position.Center)
  }

  reactions += {
    case event: StartGame =>
  }
}

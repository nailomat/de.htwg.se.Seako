package de.htwg.se.seako.aview.gui

import de.htwg.se.seako.controller.{CellChanged, Controller, GridSizeChanged}

import scala.swing._

class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)
  controller.createEmptyGrid(controller.gridSize)
  title = "Seako"
  preferredSize = new Dimension(800, 800)
  centerOnScreen()
  visible = true

  var cells = Array.ofDim[CellPanel](controller.gridSize, controller.gridSize)

  def controlPanel: BorderPanel = new BorderPanel {

  }

  def gridPanel: GridPanel = new GridPanel(controller.grid.size, controller.grid.size) {
    background = java.awt.Color.darkGray
    for {
      row <- 0 until controller.grid.size
      col <- 0 until controller.grid.size
    } {
      val cellPanel = new CellPanel(row, col, controller)
      cells(row)(col) = cellPanel
      contents += cellPanel
      listenTo(cellPanel, controller)
    }
  }

  contents = new BorderPanel {
    add(gridPanel, BorderPanel.Position.Center)
    add(controlPanel, BorderPanel.Position.East)
  }

  reactions += {
    case event: GridSizeChanged => resize()
    case event: CellChanged => redraw()
  }

  def start(): Unit = {
    contents = new BorderPanel {
      add(gridPanel, BorderPanel.Position.Center)
    }
  }

  def resize(): Unit = {
    cells = Array.ofDim[CellPanel](controller.gridSize, controller.gridSize)
    contents = new BorderPanel {
      add(gridPanel, BorderPanel.Position.Center)
    }
  }

  def redraw(): Unit = {
    gridPanel.contents.clear()
    for {
      row <- 0 until controller.gridSize
      col <- 0 until controller.gridSize
    } {
      cells(row)(col).redraw()
    }
    repaint
  }
}

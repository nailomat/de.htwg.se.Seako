package de.htwg.se.seako.aview.gui

import de.htwg.se.seako.controller.{CellChanged, Controller, GridSizeChanged}
import javax.swing.BorderFactory

import scala.swing._

class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)
  controller.createEmptyGrid(controller.gridSize)
  title = controller.gridSize.toString
  background = java.awt.Color.BLACK
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
      if (controller.cell(row, col).fog.value == 1) {
        val cellPanel = new CellPanel(row, col, controller)
        cells(row)(col) = cellPanel
        contents += cellPanel
        listenTo(cellPanel)
      } else {
        if (controller.cell(row, col).terrain.value == 1) {
          val cellPanel = new CellPanel(row, col, controller)
          cells(row)(col) = cellPanel
          listenTo(cellPanel)
        } else {
          if (controller.cell(row, col).players.length > 1) {
            val cellPanel = new CellPanel(row, col, controller)
            cells(row)(col) = cellPanel
            listenTo(cellPanel)
          }
        }
      }
    }
  }

  val startButton: Button = new Button("Start Game") {
    background = java.awt.Color.WHITE
    foreground = java.awt.Color.BLACK
    focusable = false
    border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
    visible = true
  }

  contents = new BorderPanel {
        add(gridPanel, BorderPanel.Position.Center)
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
      add(startButton, BorderPanel.Position.North)
      add(gridPanel, BorderPanel.Position.Center)
    }
  }

  def redraw(): Unit = {
    println(controller.gridSize)
    for {
      row <- 0 until controller.gridSize
      col <- 0 until controller.gridSize
    } {
      cells(row)(col).redraw()
    }
    repaint
  }
}

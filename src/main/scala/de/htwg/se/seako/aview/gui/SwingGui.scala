package de.htwg.se.seako.aview.gui

import de.htwg.se.seako.controller.{CellChanged, Controller, GridSizeChanged}
import javax.swing.ImageIcon

import scala.swing._

class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)
  controller.createEmptyGrid(controller.gridSize)
  title = "Seako"
  preferredSize = new Dimension(1200, 800)
  centerOnScreen()
  visible = true

  var cells = Array.ofDim[CellPanel](controller.gridSize, controller.gridSize)

  val currentPlayerLabel: Label = new Label {
    text = controller.playerList.players.head.name
  }

  val upButton : Button = new Button {
    val button = new Button("UP")
    background = java.awt.Color.GREEN
    foreground = java.awt.Color.BLUE
    visible = true
    button
  }
  def menuPanel: FlowPanel = new FlowPanel() {
    listenTo(controller)
    background = java.awt.Color.WHITE
    preferredSize = new Dimension (400, 800)
    visible = true

  }

  def createArrow(direction : String) : Button = {
    val button = new Button("")
    direction match {
      case "up" => button.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/UpArrow.png")
      case "down" => button.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/DownArrow.png")
      case "left" => button.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/LeftArrow.png")
      case "right" => button.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/RightArrow.png")
    }
    button.contentAreaFilled = false
    button.preferredSize = new Dimension(80, 80)
    button
  }

  def controlPanel: GridBagPanel = new GridBagPanel() {
    listenTo(controller)
    background = java.awt.Color.WHITE
    preferredSize = new Dimension(400, 400)
    visible = true
    val upButton = createArrow("up")
    val downButton = createArrow("down")
    val leftButton = createArrow("left")
    val rightButton = createArrow("right")
    val gbc = new Constraints()
    gbc.gridx = 3
    gbc.gridy = 0
    add(upButton, gbc)
    gbc.gridx = 0
    gbc.gridy = 4
    add(leftButton, gbc)
    gbc.gridx = 3
    add(downButton, gbc)
    gbc.gridx = 6
    add(rightButton, gbc)
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
      repaint
    }
  }
}
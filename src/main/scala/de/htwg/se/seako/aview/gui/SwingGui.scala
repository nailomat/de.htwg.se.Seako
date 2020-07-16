package de.htwg.se.seako.aview.gui

import de.htwg.se.seako.controller.{CellChanged, Controller, GridSizeChanged, PlayerChanged}
import javax.swing.ImageIcon

import scala.swing._
import scala.swing.event.ButtonClicked

class SwingGui(controller: Controller) extends Frame {

  listenTo(controller)
  controller.createEmptyGrid(controller.gridSize)
  title = "Seako"
  preferredSize = new Dimension(1200, 800)
  centerOnScreen()
  visible = true

  var cells = Array.ofDim[CellPanel](controller.gridSize, controller.gridSize)

  val currentPlayerIcon: Label = new Label {
    icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/CurrentPlayer.png")
  }

  val currentPlayerLabel: Label = new Label {
    listenTo(controller)
    text = controller.playerList.players.head.name
    font = new Font("Verdana", 20, 40)
    visible = true
    reactions += {
      case e: PlayerChanged => text = controller.playerList.players.head.name
    }
  }


  def createArrow(direction: String): Button = {
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

  def menuPanel: FlowPanel = new FlowPanel() {
    listenTo(controller)
    background = java.awt.Color.WHITE
    preferredSize = new Dimension(400, 800)
    visible = true
    contents += currentPlayerIcon
    contents += currentPlayerLabel
    contents += controlPanel
    contents
  }

  def controlPanel: GridBagPanel = new GridBagPanel() {
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
    reactions += {
      case ButtonClicked(b) =>
        if (b == upButton) {
          controller.movePlayer("up")
        } else if (b == downButton) {
          controller.movePlayer("down")
        } else if (b == rightButton) {
          controller.movePlayer("right")
        } else if (b == leftButton) {
          controller.movePlayer("left")
        }
    }
    listenTo(controller, upButton, downButton, leftButton, rightButton)
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
    add(menuPanel, BorderPanel.Position.East)
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
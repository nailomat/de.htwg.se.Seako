package de.htwg.se.seako.aview.gui

import de.htwg.se.seako.controller._
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

  def enemyAmount(enemy: String): Label = new Label {
    enemy match {
      case "zombie" => text = "Amount Zombies: " + controller.amountEnemies()._1.toString
      case "mutant" => text = "Amount Mutant: " + controller.amountEnemies()._2.toString
      case "boss" => text = "Amount Boss: " + controller.amountEnemies()._3.toString
    }
  }

  def menuPanel: FlowPanel = new FlowPanel() {
    listenTo(controller)
    background = java.awt.Color.WHITE
    preferredSize = new Dimension(400, 800)
    visible = true
    contents += currentPlayerIcon
    contents += currentPlayerLabel
    contents += enemyPanel
    contents += controlPanel
    contents += attackPanel
    contents
  }

  def enemyPanel: FlowPanel = new FlowPanel() {
    listenTo(controller)
    preferredSize = new Dimension(400, 200)
    val enemyIcon = new Label {
      icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/Enemies.png")
    }
    val ZombieAmount = new Label {
      text = "Amount Zombies: " + controller.amountEnemies()._1.toString
    }
    val MutantAmount = new Label {
      text = "Amount Mutants: " + controller.amountEnemies()._2.toString
    }
    val BossAmount = new Label {
      text = "Amount Boss: " + controller.amountEnemies()._3.toString
    }
    font = new Font("Verdana", 20, 40)
    visible = true
    contents += enemyIcon
    contents += ZombieAmount
    contents += MutantAmount
    contents += BossAmount
    reactions += {
      case e: ChangeEnemy => {
        println("asfawehlga")
        contents.clear()
        contents += enemyIcon
        contents += enemyAmount("zombie")
        contents += enemyAmount("mutant")
        contents += enemyAmount("boss")
        repaint
      }
    }
  }

  def controlPanel: GridBagPanel = new GridBagPanel() {
    background = java.awt.Color.WHITE
    preferredSize = new Dimension(400, 200)
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

  def attackDice(amount: Int): Label = {
    val dice: Label = new Label {
      amount match {
        case 1 => icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/Dice1.png")
        case 2 => icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/Dice2.png")
        case 3 => icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/Dice3.png")
        case 4 => icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/Dice4.png")
        case 5 => icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/Dice5.png")
        case 6 => icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/Dice6.png")
      }
      preferredSize = new Dimension(80, 80)
    }
    dice
  }

  def attackPanel: FlowPanel = new FlowPanel() {
    preferredSize = new Dimension(400, 200)
    val attackButton: Button = new Button {
      icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/Attack.png")
      preferredSize = new Dimension(400, 80)
      contentAreaFilled = false
    }
    contents += attackButton
    reactions += {
      case ButtonClicked(b) =>
        if (b == attackButton) {
          val dice = controller.attackEnemy()
          contents.clear()
          contents += attackButton
          contents += attackDice(dice.rolldice)
          repaint
        }
    }
    listenTo(attackButton)
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
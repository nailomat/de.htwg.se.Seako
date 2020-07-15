package de.htwg.se.seako.aview.gui

import de.htwg.se.seako.controller.Controller
import javax.swing.{BorderFactory, ImageIcon}

import scala.swing.event.{ButtonClicked, EditDone}
import scala.swing.{BorderPanel, Button, Dimension, FlowPanel, Font, Frame, GridBagPanel, Label, TextField}

class StartFrame(controller: Controller) extends Frame {

  listenTo(controller)
  title = "Seako"
  preferredSize = new Dimension(800, 800)
//  background = java.awt.Color.LIGHT_GRAY

  val seako: Label = new Label() {
    icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/seako.PNG")
  }

  val newButton: Button = createButton("")
  newButton.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/newGame.PNG")
  val loadButton: Button = createButton("")
  loadButton.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/loadGame.PNG")
  val exitButton: Button = createButton("")
  exitButton.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/exitGame.PNG")
  contents = new BorderPanel() {
    val contents2: GridBagPanel = new GridBagPanel() {
      val gbc = new Constraints()
      gbc.gridx = 0
      gbc.gridy = 1
      add(newButton, gbc)
      gbc.gridy = 40
      add(loadButton, gbc)
      gbc.gridy = 60
      add(exitButton, gbc)
    }
    listenTo(newButton, loadButton, exitButton)
    add(seako, BorderPanel.Position.North)
    add(contents2, BorderPanel.Position.Center)
    reactions += {
      case ButtonClicked(b) =>
        if (b == newButton) {
          newGame()
        } else if (b == loadButton) {
          println("Incoming Feature")
        } else if (b == exitButton) {
          close
        }
    }
  }

  def newGame(): Unit = {
    //    object playerName extends TextField {columns = 10}
    val playerName: TextField = new TextField(columns = 10)
    val playerNo = controller.playerList.players.size + 1
    val anotherPlayer: Button = createButton("Add another Player")
    val selectSize: Button = createButton("Continue To Size")
    listenTo(playerName, anotherPlayer, selectSize)

    contents = new FlowPanel() {
      contents += new Label("Add Name for Player" + playerNo)
      contents += playerName
      contents += anotherPlayer
      contents += selectSize
    }
    reactions += {
      case EditDone(`playerName`) =>
        controller.addPlayerList(playerName.text)
        newGame()
      case ButtonClicked(b) =>
        if (b == anotherPlayer) {
//          controller.addPlayerList(playerName.text)
          newGame()
        } else if (b == selectSize) {
          showSizes()
        }
    }
  }

  def startGame(): Unit = {
    new SwingGui(controller)
    this.close
  }

  def showSizes(): Unit = {
    val smallButton: Button = createButton("Small Playing Field")
    val mediumButton: Button = createButton("Medium Playing Field")
    val bigButton: Button = createButton("Big Playing Field")

    contents = new FlowPanel() {
      contents += smallButton
      contents += mediumButton
      contents += bigButton
    }

    listenTo(smallButton, mediumButton, bigButton)
    reactions += {
      case ButtonClicked(b) =>
        if (b == smallButton) {
          controller.createEmptyGrid(5)
          new SwingGui(controller)
          this.close()
        } else if (b == mediumButton) {
          controller.createEmptyGrid(10)
          new SwingGui(controller)
          this.close()
        } else if (b == bigButton) {
          controller.createEmptyGrid(20)
          new SwingGui(controller)
          this.close()
        }
    }
  }

  def createButton(title: String, top: Int = 10, left: Int = 10, bottom: Int = 10, right: Int = 10): Button = {
    val button = new Button(title)
    button.background = java.awt.Color.lightGray
    button.foreground = java.awt.Color.BLACK
    button.focusable = false
    button.font = new Font("Verdana", 20, 20)
    button.border = BorderFactory.createEmptyBorder(top, left, bottom, right)
    visible = true
    button
  }

  centerOnScreen()
  visible = true
  background = java.awt.Color.GRAY
}

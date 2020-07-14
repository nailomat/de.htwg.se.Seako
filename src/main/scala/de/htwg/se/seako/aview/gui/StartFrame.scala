package de.htwg.se.seako.aview.gui

import de.htwg.se.seako.controller.Controller
import de.htwg.se.seako.model.PlayerList
import javax.swing.BorderFactory

import scala.swing.event.{ButtonClicked, EditDone}
import scala.swing.{Button, Dimension, FlowPanel, Frame, Label, TextField}

class StartFrame(controller: Controller) extends Frame {

  listenTo(controller)

  title = "Seako"
  background = java.awt.Color.BLACK
  preferredSize = new Dimension(800, 800)
  centerOnScreen()
  visible = true

  var gridSize: Int = 1;

  val newButton: Button = createButton("New Game")
  val loadButton: Button = createButton("Load Game")
  listenTo(newButton, loadButton)
  contents = new FlowPanel() {
    contents += newButton
    contents += loadButton
  }
  reactions += {
    case ButtonClicked(b) =>
      if (b == newButton) {
        newGame()
      } else if (b == loadButton) {
        println("Incoming Feature")
      }
  }

  def newGame(): Unit = {
//    object playerName extends TextField {columns = 10}
    val playerName = new TextField(columns = 10)
    val playerNo = controller.playerList.players.size + 1
    val anotherPlayer : Button = createButton("Add another Player")
    val selectSize : Button = createButton("Continue To Size")

    contents = new FlowPanel() {
      contents += new Label("Add Name for Player" + playerNo)
      contents += playerName
      contents += anotherPlayer
      contents += selectSize
    }
    listenTo(playerName, anotherPlayer, selectSize)
    reactions += {
      case EditDone(`playerName`) =>
        controller.addPlayerList(playerName.text)
        newGame()
      case ButtonClicked(b) =>
        if (b == anotherPlayer) {
          controller.addPlayerList(playerName.text)
          newGame()
        } else if (b == selectSize) {
          showSizes()
        }
    }

  }


  def startGame(): Unit = {
    //    new SwingGui(controller, 3, PlayerList(Nil))
    //    this.close
    showSizes()
  }

  val smallButton: Button = createButton("Small Playing Field")
  val mediumButton: Button = createButton("Medium Playing Field")
  val bigButton: Button = createButton("Big Playing Field")

  def showSizes(): Unit = {
    contents = new FlowPanel() {
      contents += smallButton
      contents += mediumButton
      contents += bigButton
    }
    listenTo(smallButton, mediumButton, bigButton)
    reactions += {
      case ButtonClicked(b) =>
        if (b == smallButton) {
          new SwingGui(controller, 3, PlayerList(Nil))
        } else if (b == mediumButton) {
          new SwingGui(controller, 3, PlayerList(Nil))
        } else if (b == bigButton) {
          new SwingGui(controller, 3, PlayerList(Nil))
        }
        this.close()
    }
  }

//  contents = new FlowPanel() {
//    contents += newButton
//    contents += loadButton
//    contents += smallButton
//    contents += mediumButton
//    contents += bigButton
//
//  }
//
//  listenTo(newButton, loadButton, smallButton, mediumButton, bigButton)
//
//  reactions += {
//    case ButtonClicked(b) =>
//      if (b == newButton) {
//        newGame()
//      } else if (b == smallButton) {
//        gridSize = 5
//      } else if (b == mediumButton) {
//        gridSize = 10
//      } else if (b == bigButton) {
//        gridSize = 20
//      }
//      println(gridSize)
//      this.close()
//    case event: StartGame =>
//  }

  def createButton(title: String): Button = {
    val button = new Button(title)
    button.background = java.awt.Color.WHITE
    button.foreground = java.awt.Color.BLACK
    button.focusable = false
    button.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
    visible = true
    button
  }
}

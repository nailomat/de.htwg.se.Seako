package de.htwg.se.seako.aview

import de.htwg.se.seako.controller.{Controller, GameStatus}
import de.htwg.se.seako.util.Observer

class Tui(controller: Controller) extends Observer {
  controller.add(this)
  println("Type \"start\" to start the game")

  def processInputLine(input: String): Unit = {
    input match {
      case "q" =>
      case "undo" => controller.undo()
      case "redo" => controller.redo()
      case "start" => controller.startGame()
      case _ => controller.validateLongString(input)
    }
  }

  override def update(): Unit = {
    println(GameStatus.message(controller.gameStatus))
    println(controller.gridToString)
  }
}
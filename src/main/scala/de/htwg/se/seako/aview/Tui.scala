package de.htwg.se.seako.aview

import de.htwg.se.seako.controller._

import scala.swing.Reactor

class Tui(controller: Controller) extends Reactor {
  listenTo(controller)
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

  reactions += {
    case event: SetGrid => print(controller.gridToString)
    case event: GridSizeChanged => println(controller.gridToString)
    case event: CellChanged => print(controller.gridToString)
    case event: AddPlayer => print(controller.gridToString)
    case event: RemovePlayer => print(controller.gridToString)
    case event: AddEnemy => print(controller.gridToString)
    case event: RemoveEnemy => print(controller.gridToString)
    case event: ChangeEnemy => print(controller.gridToString)
  }
}
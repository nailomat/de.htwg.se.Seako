package de.htwg.se.seako.aview

import de.htwg.se.seako.controller.{Controller, GameStatus}
import de.htwg.se.seako.util.Observer

class Tui(controller: Controller) extends Observer{
  controller.add(this)
  println("Type \"start\" to start the game")

  def processInputLine(input: String): Unit= {
    input match {
      case "undo" => controller.undo()
      case "redo" => controller.redo()
      case "asdf" => println(controller.gameStatus)
      case "start" =>
        controller.startGame()
        println(controller.gameStatus)

      case "small" => controller.createEmptyGrid(5)
      case "medium" => controller.createEmptyGrid(10)
      case "big" => controller.createEmptyGrid(20)
      case "y" =>
        if (controller.gameStatus.equals(GameStatus.INSERTPLAYER)) {
          println("INSERT NAME:")
        }

      case "n" =>
        if (controller.gameStatus.equals(GameStatus.INSERTPLAYER)) {
          println("")
          controller.setGameStatus(GameStatus.CREATEGAME)
        }

      case "np" => controller.nextPlayer()
      case _ => validateLongString(input)
    }

      def validateLongString(input: String): Unit = {
        if (input.nonEmpty) {
          val splitInput = input.split(" ")
          splitInput.length match {
            case 1 =>
              if (controller.gameStatus.equals(GameStatus.INSERTPLAYER)) {
                controller.addPlayerList(splitInput(0))
                println(controller.playerList)
              } else {
                println("Command unknown")
              }
            case 3 =>
              val command = splitInput(0)
              val row = splitInput(1).toInt
              val col = splitInput(2).toInt
              command match {
                case "addZombie" => controller.addEnemy(row, col,"zombie")
                case "addBoss" => controller.addEnemy(row, col, "boss")
                case "addMutant" => controller.addEnemy(row, col, "mutant")
              }
            case 4 =>
              val command = splitInput(0)
              val row = splitInput(1).toInt
              val col = splitInput(2).toInt
              val value = splitInput(3)
              if (command.equals("addPlayer")) {
                controller.addPlayer(row, col, value)
              }
              if (command.equals("removePlayer")) {
                controller.removePlayer(row, col, value)
              }
            case _ =>
          }
        }
      }

  }



  override def update(): Unit = {
    println(GameStatus.message(controller.gameStatus))
    println(controller.gridToString)
  }
}
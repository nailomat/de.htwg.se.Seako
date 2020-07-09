package de.htwg.se.seako.aview

import de.htwg.se.seako.controller.{Controller, GameStatus}
import de.htwg.se.seako.model.Player
import de.htwg.se.seako.util.Observer

class Tui(controller: Controller) extends Observer {
  controller.add(this)
  println("Type \"start\" to start the game")

  def processInputLine(input: String): Unit = {
    input match {
      case "undo" => controller.undo()
      case "redo" => controller.redo()
      case "start" => controller.startGame()
      case "small" => controller.createEmptyGrid(5)
      case "medium" => controller.createEmptyGrid(10)
      case "big" => controller.createEmptyGrid(20)
      case "y" =>
        if (controller.gameStatus.equals(GameStatus.INSERTPLAYER)) {
          println("INSERT NAME:")
        }
      case "status" => println(controller.gameStatus)
      case "n" =>
        if (controller.gameStatus.equals(GameStatus.INSERTPLAYER)) {
          println("Select the field size:")
          println("small | medium | big")
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
          case 2 =>
            val command = splitInput(0)
            val name = splitInput(1)
            command match {
              case "playerPos" => controller.getPlayerPos(name)
            }
          case 3 =>
            val command = splitInput(0)
            val row = splitInput(1)
            val col = splitInput(2)
            command match {
              case "addCurrentPlayer" =>
                controller.setCell(row.toInt, col.toInt, controller.grid.cell(row.toInt, col.toInt)
                  .addPlayer(controller.playerList.getCurrentPlayer))
              case "addZombie" =>
                controller.setCell(row.toInt, col.toInt, controller.grid.cell(row.toInt, col.toInt)
                  .addEnemy("zombie"))
              case "addMutant" =>
                controller.setCell(row.toInt, col.toInt, controller.grid.cell(row.toInt, col.toInt)
                  .addEnemy("mutant"))
              case "addBoss" =>
                controller.setCell(row.toInt, col.toInt, controller.grid.cell(row.toInt, col.toInt)
                  .addEnemy("boss"))
              case "removeZombie" =>
                controller.setCell(row.toInt, col.toInt, controller.grid.cell(row.toInt, col.toInt)
                  .removeEnemy("zombie"))
              case "removeMutant" =>
                controller.setCell(row.toInt, col.toInt, controller.grid.cell(row.toInt, col.toInt)
                  .removeEnemy("mutant"))
              case "removeBoss" =>
                controller.setCell(row.toInt, col.toInt, controller.grid.cell(row.toInt, col.toInt)
                  .removeEnemy("boss"))
              case "move" =>
                controller.movePlayer(row, col)
              case _ =>
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
              //controller.removePlayer(row, col, value)
              controller.setCell(row, col, controller.grid.cell(row, col).removePlayer(Player(value)))
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
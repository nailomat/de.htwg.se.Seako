package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.model.Player
import de.htwg.se.seako.Seako.controller._


class GameStateContext {
  var state: State = _

  def validateString(input: String): Unit = {
    val splitInput = input.split(" ")
    println(state)
    splitInput.length match {
      case 1 =>
        splitInput(0) match {
          case "start" =>
            state = Start
          case "small" if state == CreateGame =>
            state = CreateSmallGame
            state = NewRound
          case "medium" if state == CreateGame =>
            state = CreateMediumGame
            state = NewRound
          case "big" if state == CreateGame =>
            state = CreateBigGame
            state = NewRound
          case "y" if state ==  InsertPlayer =>
            state = InsertPlayer
          case "n" if state ==  InsertPlayer =>
            state = CreateGame
          case _ if state == InsertPlayer =>
            addPlayerList(splitInput(0))
            println(playerList)
            println("Add another player?")
            println("y | n")

          case _ if state == CreateGame =>
            println("unknown size")
        }
        if (splitInput(0).equals("np")) {
          nextPlayer()
        }
        if (splitInput(0).equals("attack")) {
          attackEnemy()
        }
      case 2 =>
        val command = splitInput(0)
        val value = splitInput(1)
        command match {
          case "move" =>
            movePlayer(value)
          case _ =>
        }
      case 3 =>
        val command = splitInput(0)
        val row = splitInput(1)
        val col = splitInput(2)
        command match {
          case "addCurrentPlayer" =>
            setCell(row.toInt, col.toInt, grid.cell(row.toInt, col.toInt)
              .addPlayer(playerList.getCurrentPlayer))
          case "addZombie" =>
            setCell(row.toInt, col.toInt, grid.cell(row.toInt, col.toInt)
              .addEnemy("zombie"))
          case "addMutant" =>
            setCell(row.toInt, col.toInt, grid.cell(row.toInt, col.toInt)
              .addEnemy("mutant"))
          case "addBoss" =>
            setCell(row.toInt, col.toInt, grid.cell(row.toInt, col.toInt)
              .addEnemy("boss"))
          case "removeZombie" =>
            setCell(row.toInt, col.toInt, grid.cell(row.toInt, col.toInt)
              .removeEnemy(Some("zombie")))
          case "removeMutant" =>
            setCell(row.toInt, col.toInt, grid.cell(row.toInt, col.toInt)
              .removeEnemy(Some("mutant")))
          case "removeBoss" =>
            setCell(row.toInt, col.toInt, grid.cell(row.toInt, col.toInt)
              .removeEnemy(Some("boss")))
          case _ =>
        }
      case 4 =>
        val command = splitInput(0)
        val row = splitInput(1).toInt
        val col = splitInput(2).toInt
        val value = splitInput(3)
        if (command.equals("addPlayer")) {
          addPlayer(row, col, value)
        }
        if (command.equals("removePlayer")) {
          setCell(row, col, grid.cell(row, col).removePlayer(Player(value)))
        }

      case _ =>
    }
  }

}

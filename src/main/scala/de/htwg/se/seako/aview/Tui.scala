package de.htwg.se.seako.aview

import de.htwg.se.seako.controller.GameState.{CreateBigGameState, CreateGameState, CreateMediumGameState, CreateSmallGameState, GameStateContext, InsertPlayerState, NewRoundState, PlayerTurnState, StartState}
import de.htwg.se.seako.controller._
import de.htwg.se.seako.model.Player

import scala.swing.Reactor

class Tui(controller: Controller) extends Reactor {
  listenTo(controller)
 // println("Type \"start\" to start the game")

  def processInputLine(input: String): Unit = {
    input match {
      case "q" =>
      case "undo" => controller.undo()
      case "redo" => controller.redo()
      case "start" => GameStateContext.handle(new StartState)
      case _ => validateLongString(input)
    }
  }
  def validateLongString(input: String): Unit ={
    val splitInput = input.split(" ")
    splitInput.length match {
      case 1 =>
        splitInput(0) match {
          case "start" =>
            GameStateContext.handle(new StartState)
          case "y" =>
            GameStateContext.handle(InsertPlayerState(input))
          case "n" =>
            GameStateContext.handle(CreateGameState(input))
          case _ =>
            GameStateContext.handle(InsertPlayerState(input))
          case "small" =>
            GameStateContext.handle(CreateGameState(input))
          case "medium" =>
            GameStateContext.handle(CreateGameState(input))
          case "big"=>
            GameStateContext.handle(CreateGameState(input))


          case _ =>
            println("unknown size")
        }
        if (splitInput(0).equals("np")) {
          controller.nextPlayer()
        }
        if (splitInput(0).equals("attack")) {
          GameStateContext.handle(PlayerTurnState())
        }
        GameStateContext
      case 2 =>
        val command = splitInput(0)
        val value = splitInput(1)
        command match {
          case "move" =>
            controller.movePlayer(value)
            GameStateContext
          case _ =>
            GameStateContext
        }
      case 3 =>
        val command = splitInput(0)
        val row = splitInput(1)
        val col = splitInput(2)
        command match {
          case "addCurrentPlayer" =>
            controller.setCell(row.toInt, col.toInt, controller.grid.cell(row.toInt, col.toInt)
              .addPlayer(controller.playerList.getCurrentPlayer))
            GameStateContext
          case "addZombie" =>
            controller.setCell(row.toInt, col.toInt, controller.grid.cell(row.toInt, col.toInt)
              .addEnemy("zombie"))
            GameStateContext
          case "addMutant" =>
            controller.setCell(row.toInt, col.toInt, controller.grid.cell(row.toInt, col.toInt)
              .addEnemy("mutant"))
            GameStateContext
          case "addBoss" =>
            controller.setCell(row.toInt, col.toInt, controller.grid.cell(row.toInt, col.toInt)
              .addEnemy("boss"))
            GameStateContext
          case "removeZombie" =>
            controller.setCell(row.toInt, col.toInt, controller.grid.cell(row.toInt, col.toInt)
              .removeEnemy(Some("zombie")))
            GameStateContext
          case "removeMutant" =>
            controller.setCell(row.toInt, col.toInt, controller.grid.cell(row.toInt, col.toInt)
              .removeEnemy(Some("mutant")))
            GameStateContext
          case "removeBoss" =>
            controller.setCell(row.toInt, col.toInt, controller.grid.cell(row.toInt, col.toInt)
              .removeEnemy(Some("boss")))
            GameStateContext
          case _ =>
            GameStateContext
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
          controller.setCell(row, col, controller.grid.cell(row, col).removePlayer(Player(value)))
        }
        GameStateContext

      case _ => GameStateContext
    }
  }


  reactions += {
    case event: SetGrid => print(controller.gridToString)
    case event: CellChange => print(controller.gridToString)
    case event: AddPlayer => print(controller.gridToString)
    case event: RemovePlayer => print(controller.gridToString)
    case event: AddEnemy => print(controller.gridToString)
    case event: RemoveEnemy => print(controller.gridToString)
    case event: ChangeEnemy => print(controller.gridToString)
  }
}
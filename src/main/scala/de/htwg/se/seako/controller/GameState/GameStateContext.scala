package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.model.Player
import de.htwg.se.seako.Seako.controller._

import scala.swing.event.Event


case class GameStateContext() extends State {

  override def validateString(input: String): State = {

    val splitInput = input.split(" ")
    splitInput.length match {
      case 1 =>
        splitInput(0) match {
          case "start" =>
            Start.handle(GameStateContext())
          case "small" =>
            CreateSmallGame.handle(GameStateContext())
            NewRound.handle(GameStateContext())
          case "medium" =>
            CreateMediumGame.handle(GameStateContext())
            NewRound.handle(GameStateContext())
          case "big"=>
            CreateBigGame.handle(GameStateContext())
            NewRound.handle(GameStateContext())
          case "y" =>
           InsertPlayer.handle(GameStateContext())
          case "n" =>
            CreateGame.handle(GameStateContext())
          case _ =>
            addPlayerList(splitInput(0))
            println(playerList)
            println("Add another player?")
            println("y | n")

          case _ =>
            println("unknown size")
        }
        if (splitInput(0).equals("np")) {
          nextPlayer()
        }
        if (splitInput(0).equals("attack")) {
          PlayerTurn.handle(GameStateContext())
         }
        GameStateContext()
      case 2 =>
        val command = splitInput(0)
        val value = splitInput(1)
        command match {
          case "move" =>
            movePlayer(value)
            GameStateContext()
          case _ =>
            GameStateContext()
        }
      case 3 =>
        val command = splitInput(0)
        val row = splitInput(1)
        val col = splitInput(2)
        command match {
          case "addCurrentPlayer" =>
            setCell(row.toInt, col.toInt, grid.cell(row.toInt, col.toInt)
              .addPlayer(playerList.getCurrentPlayer))
            GameStateContext()
          case "addZombie" =>
            setCell(row.toInt, col.toInt, grid.cell(row.toInt, col.toInt)
              .addEnemy("zombie"))
            GameStateContext()
          case "addMutant" =>
            setCell(row.toInt, col.toInt, grid.cell(row.toInt, col.toInt)
              .addEnemy("mutant"))
            GameStateContext()
          case "addBoss" =>
            setCell(row.toInt, col.toInt, grid.cell(row.toInt, col.toInt)
              .addEnemy("boss"))
            GameStateContext()
          case "removeZombie" =>
            setCell(row.toInt, col.toInt, grid.cell(row.toInt, col.toInt)
              .removeEnemy(Some("zombie")))
            GameStateContext()
          case "removeMutant" =>
            setCell(row.toInt, col.toInt, grid.cell(row.toInt, col.toInt)
              .removeEnemy(Some("mutant")))
            GameStateContext()
          case "removeBoss" =>
            setCell(row.toInt, col.toInt, grid.cell(row.toInt, col.toInt)
              .removeEnemy(Some("boss")))
            GameStateContext()
          case _ =>
            GameStateContext()
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
        GameStateContext()

      case _ => GameStateContext()
    }
  }

}

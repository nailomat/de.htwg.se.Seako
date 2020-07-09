package de.htwg.se.seako.controller

import de.htwg.se.seako.controller.GameStatus._
import de.htwg.se.seako.model._
import de.htwg.se.seako.util.{Observable, UndoManager}

class Controller(var grid: Grid[Cell], var playerList: PlayerList) extends Observable {

  val undoManager = new UndoManager
  var gameStatus: GameStatus = START

  def setGameStatus(status: GameStatus): Unit = {
    gameStatus = status
  }

  def startGame(): Unit = {
    println("GAME HAS STARTED")
    println("INSERT PLAYER  NAME:")
    gameStatus = INSERTPLAYER
  }

  def addPlayerList(playerName: String): Unit = {
    playerList = playerList.addPlayer(Player(playerName))
    println("Add another player?")
    println("y | n")
    println(playerList.getCurrentPlayer)
  }

  def nextPlayer(): Unit = {
    playerList = playerList.nextPlayer
    println("It's " + playerList.getCurrentPlayer + "'s turn")
  }

  def createEmptyGrid(size: Int): Unit = {
    grid = new Grid[Cell](size, Cell(Nil, Enemies(Nil), Terrain(0), Fog(1)))
    notifyObservers()
  }

  def addPlayer(row: Int, col: Int, name: String): Unit = {
    grid = grid.replaceCell(row, col, grid.cell(row, col).addPlayer(Player(name)))
    notifyObservers()
  }

  def removePlayer(row: Int, col: Int, name: String): Unit = {
    grid = grid.replaceCell(row, col, grid.cell(row, col).removePlayer(Player(name)))
    notifyObservers()
  }

  def addEnemy(row: Int, col: Int, enemy: String): Unit = {
    grid = grid.replaceCell(row, col, grid.cell(row, col).addEnemy(enemy))
    notifyObservers()
  }

  def removeEnemy(row: Int, col: Int, enemy: String): Unit = {
    grid = grid.replaceCell(row, col, grid.cell(row, col).removeEnemy(enemy))
    notifyObservers()
  }

  //  def removeZombie(row: Int, col: Int): Unit = {
  //    grid = grid.replaceCell(row, col, grid.cell(row, col))
  //    notifyObservers()
  //  }

  //
  //  def getPlayerPos(name: String): Unit = {
  //    grid.
  //  }
  //
  //  def movePlayer(row: Int, col: Int, name: String) : Unit = {
  //    grid = grid.replaceCell(row, col, grid.cell(r))
  //  }

  def gridToString: String = grid.toString

  def setCell(row: Int, col: Int, cell: Cell): Unit = {
    undoManager.doStep(new SetCommand(row, col, cell, this))
    notifyObservers()
  }

  def undo(): Unit = {
    undoManager.undoStep()
    notifyObservers()
  }

  def redo(): Unit = {
    undoManager.redoStep()
    notifyObservers()
  }

  def validateLongString(input: String): Unit = {
    if (input.nonEmpty) {
      val splitInput = input.split(" ")
      splitInput.length match {
        case 1 =>
          if (gameStatus.equals(GameStatus.INSERTPLAYER)) {
            splitInput(0) match {
            case "y" =>
                println("INSERT NAME:")
            case "n" =>
              println("SELECT FIELD SIZE:")
              println("small | medium | big")
              setGameStatus(GameStatus.CREATEGAME);
            case _ =>
              addPlayerList(splitInput(0))
              println(playerList)
            }
          }
          if (splitInput(0).equals("np")) {
            nextPlayer()
          }
        case 3 =>
          val command = splitInput(0)
          val row = splitInput(1).toInt
          val col = splitInput(2).toInt
          command match {
            case "addCurrentPlayer" =>
              setCell(row, col, grid.cell(row, col)
                .addPlayer(playerList.getCurrentPlayer))
            case "addZombie" =>
              setCell(row, col, grid.cell(row, col)
                .addEnemy("zombie"))
            case "addMutant" =>
              setCell(row, col, grid.cell(row, col)
                .addEnemy("mutant"))
            case "addBoss" =>
              setCell(row, col, grid.cell(row, col)
                .addEnemy("boss"))
            case "removeZombie" =>
              setCell(row, col, grid.cell(row, col)
                .removeEnemy("zombie"))
            case "removeMutant" =>
              setCell(row, col, grid.cell(row, col)
                .removeEnemy("mutant"))
            case "removeBoss" =>
              setCell(row, col, grid.cell(row, col)
                .removeEnemy("boss"))
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

}

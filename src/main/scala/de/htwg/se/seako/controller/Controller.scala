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

}

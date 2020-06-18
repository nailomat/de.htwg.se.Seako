package de.htwg.se.seako.controller

import de.htwg.se.seako.controller.GameStatus._
import de.htwg.se.seako.model._
import de.htwg.se.seako.util.Observable

class Controller(var grid: Grid[Cell], var playerList: PlayerList) extends Observable {

  var gameStatus: GameStatus = START

  def setGameStatus(status: GameStatus): Unit = {
    gameStatus = status
  }

  def startGame(): Unit = {
    println("GAME HAS STARTED")
    println("PLAYER ONE INSERT NAME:")
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
    grid = new Grid[Cell](size, Cell(Nil, Nil, Terrain(0), Fog(1)))
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

  def addZombie(row: Int, col: Int): Unit = {
    grid = grid.replaceCell(row, col, grid.cell(row, col).addZombie(Zombie(1, 0)))
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

}

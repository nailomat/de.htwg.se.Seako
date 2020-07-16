package de.htwg.se.seako.controller

import de.htwg.se.seako.controller.GameState.GameStateContext
import de.htwg.se.seako.model._
import de.htwg.se.seako.util.UndoManager

import scala.swing.Publisher

class Controller(var grid: Grid[Cell], var playerList: PlayerList, var gameStateContext: GameStateContext) extends Publisher {

  val undoManager = new UndoManager


  def startGame(input: String): Unit = {
    gameStateContext.validateString(input)
  }

  def addPlayerList(playerName: String): Unit = {
    playerList = playerList.addPlayer(Player(playerName))
    println(playerList.getCurrentPlayer)
  }

  def nextPlayer(): Unit = {
    playerList = playerList.nextPlayer
    println("It's " + playerList.getCurrentPlayer + "'s turn")
  }

  def createEmptyGrid(size: Int): Unit = {
    grid = new Grid[Cell](size, Cell(Nil, Enemies(Nil), Terrain(0), Fog(1)))
    publish(new SetGrid)
  }

  def addPlayer(row: Int, col: Int, name: String): Unit = {
    grid = grid.replaceCell(row, col, grid.cell(row, col).addPlayer(Player(name)))
    publish(new AddPlayer)
  }

  def removePlayer(row: Int, col: Int, name: String): Unit = {
    grid = grid.replaceCell(row, col, grid.cell(row, col).removePlayer(Player(name)))
    publish(new RemovePlayer)
  }

  def addEnemy(row: Int, col: Int, enemy: String): Unit = {
    grid = grid.replaceCell(row, col, grid.cell(row, col).addEnemy(enemy))
    publish(new AddEnemy)
  }

  def removeEnemy(row: Int, col: Int, enemy: Option[String]): Unit = {
    grid = grid.replaceCell(row, col, grid.cell(row, col).removeEnemy(enemy))
    publish(new RemoveZombie)
  }


  //FUNKTION IS NOT USED! ONLY FOR TEST PURPOSE
  def getPlayerPos(name: String): Unit = {
    val currentcell = grid.playerPos(Player(name))
    println("Player " + name + " is Currently on " + currentcell)
    //notifyObservers()
  }

  def movePlayer(direction: String): Unit = {
    val name = playerList.getCurrentPlayer
    val position = grid.playerPos(name)
    grid = grid.replaceCell(grid.movePlayer(name,direction)._1, grid.movePlayer(name, direction)._2, grid.cell(grid.movePlayer(name,direction)._1, grid.movePlayer(name, direction)._2).addPlayer(name))
    grid = grid.replaceCell(position._1, position._2, grid.cell(position._1, position._2).removePlayer(name))
    publish(new CellChange)
  }

  def attackEnemy(): Unit = {
    val attackPower = new Dice
    val name = playerList.getCurrentPlayer
    val position = grid.playerPos(name)
    removeEnemy(position._1, position._2, grid.cell(position._1, position._2).attackEnemy(name, attackPower.rolldice))
    publish(new ChangeEnemy)
  }

  def gridToString: String = grid.toString

  def setCell(row: Int, col: Int, cell: Cell): Unit = {
    undoManager.doStep(new SetCommand(row, col, cell, this))
    publish(new CellChange)
  }

  def cell(row: Int, col: Int): Cell = grid.cell(row, col)

  def gridSize = grid.size

  def undo(): Unit = {
    undoManager.undoStep()
    publish(new CellChange)
  }

  def redo(): Unit = {
    undoManager.redoStep()
    publish(new CellChange)
  }

  def validateLongString(input: String): Unit ={
    gameStateContext.validateString(input)
  }

}

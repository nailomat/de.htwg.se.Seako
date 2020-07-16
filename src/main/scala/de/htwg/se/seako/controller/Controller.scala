package de.htwg.se.seako.controller

import de.htwg.se.seako.controller.GameStatus._
import de.htwg.se.seako.model._
import de.htwg.se.seako.util.UndoManager

import scala.swing.Dialog.Options
import scala.swing.Publisher

class Controller(var grid: Grid[Cell], var playerList: PlayerList) extends Publisher {

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
    publish(new PlayerChanged)
  }

  def createEmptyGrid(size: Int): Unit = {
    grid = new Grid[Cell](size, Cell(Nil, Enemies(Nil), Terrain(0), Fog(1)))
    publish(new GridSizeChanged(size))
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
    publish(new CellChanged)
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
    publish(new CellChanged)
  }

  def cell(row: Int, col: Int): Cell = grid.cell(row, col)

  def gridSize: Int = grid.size

  def undo(): Unit = {
    undoManager.undoStep()
    publish(new CellChanged)
  }

  def redo(): Unit = {
    undoManager.redoStep()
    publish(new CellChanged)
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
                setGameStatus(GameStatus.CREATEGAME)
              case _ =>
                addPlayerList(splitInput(0))
                println(playerList)
            }
          } else if (gameStatus.equals(GameStatus.CREATEGAME)) {
              splitInput(0) match {
                case "small" =>
                  createEmptyGrid(5)
                  setGameStatus(GameStatus.NEWROUND)
                case "medium" =>
                  createEmptyGrid(10)
                  setGameStatus(GameStatus.NEWROUND)
                case "big" =>
                  createEmptyGrid(20)
                  setGameStatus(GameStatus.NEWROUND)
                case _ => println("unknown size")
              }
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
              publish(new CellChanged)
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

}

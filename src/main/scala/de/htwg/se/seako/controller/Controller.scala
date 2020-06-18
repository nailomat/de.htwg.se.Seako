package de.htwg.se.seako.controller

import de.htwg.se.seako.controller.GameStatus._
import de.htwg.se.seako.model._
import de.htwg.se.seako.util.Observable

class Controller(var grid: Grid[Cell]) extends Observable{

  var gameStatus: GameStatus = INSERTPLAYER

  def createEmptyGrid(size:Int) : Unit = {
    grid = new Grid[Cell](size, Cell(Nil, Nil, Terrain(0), Fog(1)))
    notifyObservers()
  }

  def addPlayer(row: Int, col: Int, name: String): Unit = {
    grid = grid.replaceCell(row, col, grid.cell(row, col).addPlayer(Player(name)))
    notifyObservers()
  }

  def removePlayer(row : Int, col: Int, name: String): Unit = {
    grid = grid.replaceCell(row, col, grid.cell(row, col).removePlayer(Player(name)))
    notifyObservers()
  }

  def addZombie(row: Int, col: Int): Unit = {
    grid = grid.replaceCell(row, col, grid.cell(row, col).addZombie(Zombie(1,0)))
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

package de.htwg.se.seako.controller

import de.htwg.se.seako.model._
import de.htwg.se.seako.util.Observable

class Controller(var grid: Grid[Cell]) extends Observable {
  def createEmptyGrid(size: Int): Unit = {
    grid = new Grid[Cell](size, Cell(Nil, Nil, Terrain(0), Fog(1)))
    notifyObservers()
  }

  def addZombie(row: Int, col: Int): Unit = {
    grid = grid.replaceCell(row, col, grid.cell(row, col).addZombie(Zombie(1, 0)))
    notifyObservers()
  }

  def removeZombie(row: Int, col: Int): Unit = {
    grid = grid.replaceCell(row, col, grid.cell(row, col))
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

  def getPlayerPos(name: String): String = {
    var output = ""
    for (row <- 0 until grid.size) {
      for (col <- 0 until grid.size) {
        if (grid.rows(row)(col).playerRow().contains(name)) {
          output = row + " " + col

        }
      }
    }
    output
  }

//
//  def movePlayer(row: Int, col: Int, name: String) : Unit = {
//     grid
//     grid = grid.replaceCell(row, col, grid.cell())
//  }

  def gridToString: String = grid.toString

}

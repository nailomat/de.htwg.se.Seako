package de.htwg.se.seako.controller

import de.htwg.se.seako.model.{Cell, Fog, Grid, Terrain, Zombie}
import de.htwg.se.seako.util.Observable

class Controller(var grid: Grid[Cell]) extends Observable{
  def createEmptyGrid(size:Int) : Unit = {
    grid = new Grid[Cell](size, Cell(Nil, Nil, Terrain(0), Fog(1)))
    notifyObservers()
  }

//  def set(row: Int, col: Int, value: Int) : Unit = {
//    grid = grid.replaceCell(row, col,value)
//  }

  def addZombie(row: Int, col: Int): Unit = {
    grid = grid.replaceCell(row, col, grid.cell(row, col).addZombie(Zombie(1,0)))
  }

  def gridToString: String = grid.toString

}

package de.htwg.se.seako.controller

import de.htwg.se.seako.model.{Cell, Fog, Grid, Terrain}

class Controller(var grid: Grid[Cell]) {
  def createEmptyGrid(size:Int): Unit = {
    grid = new Grid[Cell](size, Cell(Nil, Nil, Terrain(0), Fog(1)))
  }
}

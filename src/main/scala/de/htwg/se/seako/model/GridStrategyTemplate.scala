package de.htwg.se.seako.model

trait GridStrategyTemplate {

  def createEmptyGrid(size: Int): Grid[Cell] = {
    var grid = new Grid[Cell](size, Cell(Nil, Enemies(Nil), Terrain(0), Fog(1)))
    grid
  }

  def fillTerrain(): Grid[Cell]

  def fillPlayer(playerList: PlayerList): Grid[Cell]

}

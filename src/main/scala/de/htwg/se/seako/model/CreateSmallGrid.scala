//package de.htwg.se.seako.model
//
//class CreateSmallGrid(playerList: PlayerList) extends GridStrategyTemplate {
//
//  var grid = createEmptyGrid(5)
//
//  override def fillTerrain(): Grid[Cell] = {
//    val startCell = new Cell(playerList.players, Enemies(Nil), Terrain(0), Fog(0))
//    val terrainCell = new Cell(Nil,Enemies(Nil),Terrain(1), Fog(1))
//    grid.replaceCell(1, 1, terrainCell)
//    grid.replaceCell(0,0, startCell)
//  }
//
//  override def fillPlayer(playerList: PlayerList): Grid[Cell] = {}
//
//}

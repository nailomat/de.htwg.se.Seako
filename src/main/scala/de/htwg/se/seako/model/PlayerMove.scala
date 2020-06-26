package de.htwg.se.seako.model

case class PlayerMove(player: PlayerList, grid: Grid[Cell]) {

  def moveUpCol: Int = {
    val playerOnTurn = player.getCurrentPlayer
    var playerPosCol = grid.playerPosCol(playerOnTurn)
    if(playerPosCol > 0) {
      playerPosCol = playerPosCol - 1
    }
    playerPosCol
  }
  def moveUpRow: Int = {
    val playerOnTurn = player.getCurrentPlayer
    val playerPosRow = grid.playerPosRow(playerOnTurn)
    playerPosRow
  }


//
//  def moveDown
//  def moveLeft
//  def moveRight
}

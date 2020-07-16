package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._

object CreateMediumGameState {

  def createMediumGame:Unit = {
    val numberPlayers = playerList.players.size
    createEmptyGrid(10)
    for(numberPlayers <- playerList.players) {
      setCell(0, 0, grid.cell(0, 0)
        .addPlayer(playerList.getCurrentPlayer))
    }
  }
}

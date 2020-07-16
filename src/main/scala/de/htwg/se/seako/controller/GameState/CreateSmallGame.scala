package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._

object CreateSmallGame extends State {
  var state = createSmallGame
  override def handle(e: State): Unit ={
    e match {
      case CreateSmallGame => state = createSmallGame
    }
    createSmallGame
  }



  def createSmallGame:Unit = {
    val numberPlayers = playerList.players.size
    createEmptyGrid(5)
    for (numberPlayers <- playerList.players) {
      setCell(0, 0, grid.cell(0, 0)
        .addPlayer(playerList.getCurrentPlayer))
    }

  }

}

package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._

object CreateBigGame {
    var state: Unit = _
    def handle(e: State): Unit ={
      e match {
        case on: GameStateContext => state = createBigGame
      }
      state
    }


      def createBigGame:Unit = {
      val numberPlayers = playerList.players.size
      createEmptyGrid(20)
      for(numberPlayers <- playerList.players) {
        setCell(0, 0, grid.cell(0, 0)
          .addPlayer(playerList.getCurrentPlayer))
      }

    }

}

package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._

object CreateBigGame extends State {
    var state = createBigGame
    override def handle(e: State): Unit ={
      e match {
        case CreateBigGame => state = createBigGame
      }
      createBigGame
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

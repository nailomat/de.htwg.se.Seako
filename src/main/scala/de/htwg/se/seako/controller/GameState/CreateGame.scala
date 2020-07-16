package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._
import de.htwg.se.seako.model.Cell

object CreateGame extends State {
  var state = createGame
  override def handle(e: State): Unit ={
    e match {
      case CreateGame => state = createGame
    }
    createGame
  }



  def createGame:Unit = {
        println("SELECT FIELD SIZE:")
        println("small | medium | big")


        createEmptyGrid(5)
        for (numberPlayers <- playerList.players) {
          setCell(0, 0, grid.cell(0, 0)
            .addPlayer(playerList.getCurrentPlayer))
        }

    }


}

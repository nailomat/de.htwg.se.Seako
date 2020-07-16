package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._
import de.htwg.se.seako.model.Cell

object CreateGame {
  var state: Unit = _
  def handle(e: State): Unit ={
    e match {
      case on: GameStateContext => state = createGame
    }
    state
  }



  def createGame:Unit = {
        println("SELECT FIELD SIZE:")
        println("small | medium | big")
    }


}

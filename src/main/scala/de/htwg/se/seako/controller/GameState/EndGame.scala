package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._
import de.htwg.se.seako.model.Cell

object EndGame {
  var state : Unit = _
  def handle(e: State): Unit ={
    e match {
      case on: GameStateContext => state = endGame
    }
    state
  }




  def endGame:Unit = {
    println("THIS IS THE END")
  }

}

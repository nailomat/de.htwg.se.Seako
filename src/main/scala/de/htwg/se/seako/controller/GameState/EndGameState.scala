package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._
import de.htwg.se.seako.model.Cell

case class EndGameState() extends State {
  override def handle(e: State): State = endGame

  def endGame:State = {
    println("THIS IS THE END")
    GameStateContext.state
  }

}

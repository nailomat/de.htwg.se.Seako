package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._
import de.htwg.se.seako.model.Cell

case class PlayerTurnState(input: String) extends State {
  override def handle(e: State):State = GameStateContext.state
  println(GameStateContext.state)


  def playerTurn:State = {
    movePlayer(input)
    attackEnemy()
    nextPlayer()
    GameStateContext.state
  }


}

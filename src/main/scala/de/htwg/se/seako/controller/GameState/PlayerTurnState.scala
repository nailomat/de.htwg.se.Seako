package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._
import de.htwg.se.seako.model.Cell

case class PlayerTurnState() extends State {
  override def handle(e: State):State = playerTurn

  def playerTurn:State = {
    movePlayer("down")
    attackEnemy()
    nextPlayer()
    GameStateContext.state
  }


}

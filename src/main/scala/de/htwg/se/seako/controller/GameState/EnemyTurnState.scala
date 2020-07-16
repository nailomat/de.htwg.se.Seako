package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._
import de.htwg.se.seako.model.Cell

case class EnemyTurnState () extends State {
  override def handle(e: State): State =enemyTurn




  def enemyTurn:State = {
    GameStateContext.state
  }

}

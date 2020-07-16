package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._
import de.htwg.se.seako.model.Cell

object PlayerTurn{
  var state : Unit = _
  def handle(e: State): Unit ={
    e match {
      case on: GameStateContext => state = playerTurn
    }
    state
  }

  def playerTurn:Unit = {
    movePlayer("down")
    attackEnemy()
    nextPlayer()
    state = EnemyTurn
  }


}

package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._
import de.htwg.se.seako.model.Cell

object EnemyTurn extends State {
  var state = enemyTurn
  override def handle(e: State): Unit ={
    e match {
      case EnemyTurn => state = enemyTurn
    }
    enemyTurn
  }




  def enemyTurn:Unit = {

  }

}

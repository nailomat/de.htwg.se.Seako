package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._
import de.htwg.se.seako.model.Cell

object PlayerTurn extends State {
  var state = playerTurn
  override def handle(e: State): Unit ={
    e match {
      case PlayerTurn => state = playerTurn
    }
    playerTurn
  }




  def playerTurn:Unit = {

  }


}

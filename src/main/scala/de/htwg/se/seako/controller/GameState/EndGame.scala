package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._
import de.htwg.se.seako.model.Cell

object EndGame extends State {
  var state = endGame
  override def handle(e: State): Unit ={
    e match {
      case EndGame => state = endGame
    }
    endGame
  }




  def endGame:Unit = {
    println("THIS IS THE END")
  }

}

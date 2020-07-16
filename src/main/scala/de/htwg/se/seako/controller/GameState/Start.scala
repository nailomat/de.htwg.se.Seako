package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.model.Cell
import de.htwg.se.seako.Seako.controller._

object Start {

  var state: Unit = _
  def handle(e: State): Unit ={
    e match{
    case on: GameStateContext => state = start
    }
    state
  }

  def start:Unit = {
    println("GAME HAS STARTED")
    state = InsertPlayer
  }


}

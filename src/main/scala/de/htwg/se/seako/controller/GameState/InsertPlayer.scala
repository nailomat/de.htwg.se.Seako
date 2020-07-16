package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._
import de.htwg.se.seako.model.{Cell, Player}

object InsertPlayer {
  var state: Unit = _
  def handle(e: State): Unit ={
    e match {
      case on: GameStateContext => state = insertPlayer
    }
    state
  }

  def insertPlayer:Unit = {
    println("INSERT NAME:")

  }
}

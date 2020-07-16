package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._
import de.htwg.se.seako.model.{Cell, Player}

object InsertPlayer extends State {
  var state = insertPlayer
  override def handle(e: State): Unit ={
    e match {
      case InsertPlayer => state = insertPlayer
    }
    insertPlayer
  }


  def insertPlayer:Unit = {
    println("INSERT NAME:")

  }
}

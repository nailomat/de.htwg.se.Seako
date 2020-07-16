package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.model.Cell
import de.htwg.se.seako.Seako.controller._

object Start extends State {

  var state = start
  override def handle(e: State): Unit ={
    e match {
      case Start => state = start
    }
    start
  }



  def start:Unit = {
    println("GAME HAS STARTED")
    state = InsertPlayer
  }


}

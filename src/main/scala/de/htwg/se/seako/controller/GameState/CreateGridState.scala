package de.htwg.se.seako.controller.GameState

object CreateGridState {
  var state: Unit = _
  def handle(e:State): Unit = {
    e match {
      case on: GameStateContext => state = createGridState
    }
  }

  def createGridState: Unit = {
//    CreateSmallGrid()
  }
}

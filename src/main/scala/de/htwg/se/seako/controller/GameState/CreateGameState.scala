package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._
import de.htwg.se.seako.model.Cell

case class CreateGameState(input: String) extends State {

  println("SELECT FIELD SIZE:")
  println("small | medium | big")

  override def handle(e: State):State = GameStateContext.state

  def createGame():State = {
    input match {
      case "small" => CreateSmallGameState
      case "medium" => CreateMediumGameState
      case "big" => CreateBigGameState
    }
    NewRoundState()

  }

//  override def handle(e: State):State = NewRoundState()





}

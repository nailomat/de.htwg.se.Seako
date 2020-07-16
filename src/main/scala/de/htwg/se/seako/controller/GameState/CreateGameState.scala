package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._
import de.htwg.se.seako.model.Cell

case class CreateGameState(input: String) extends State {

  override def handle(e: State):State = GameStateContext.state

  def createGame():State = {
    if (input == "n"){
      println("SELECT FIELD SIZE:")
      println("small | medium | big")
      GameStateContext.state
    }
    else {
      input match {
        case "small" =>
          createSmallGame
          GameStateContext.handle(NewRoundState())
        case "medium" =>
          createMediumGame
          GameStateContext.handle(NewRoundState())
        case "big" =>
          createBigGame
          GameStateContext.handle(NewRoundState())      }
      GameStateContext.state
    }
  }

//  override def handle(e: State):State = NewRoundState()

  def createSmallGame:Unit = {
    val numberPlayers = playerList.players.size
    createEmptyGrid(5)
    for (numberPlayers <- playerList.players) {
      setCell(0, 0, grid.cell(0, 0)
        .addPlayer(playerList.getCurrentPlayer))
      nextPlayer()
    }
  }

  def createMediumGame:Unit = {
    val numberPlayers = playerList.players.size
    createEmptyGrid(10)
    for(numberPlayers <- playerList.players) {
      setCell(0, 0, grid.cell(0, 0)
        .addPlayer(playerList.getCurrentPlayer))
      nextPlayer()
    }
  }


  def createBigGame:Unit = {
    val numberPlayers = playerList.players.size
    createEmptyGrid(20)
    for(numberPlayers <- playerList.players) {
      setCell(0, 0, grid.cell(0, 0)
        .addPlayer(playerList.getCurrentPlayer))
      nextPlayer()
    }
  }

}

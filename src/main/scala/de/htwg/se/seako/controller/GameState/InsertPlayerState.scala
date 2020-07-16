package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.Seako.controller._


case class InsertPlayerState(input: String) extends State {

  override def handle(e: State):State = GameStateContext.handle(WaitForInputState())

  def insertPlayer():State = {
    if(input == "y"){
      println("Add player name")
    }else{
      addPlayerList(input)
      println(playerList)
      println("Add another player?")
      println("y | n")
    }
    GameStateContext.handle(WaitForInputState())
  }


}

package de.htwg.se.seako.controller.GameState

case class StartState() extends State {

  override def handle(e: State): State =  InsertPlayerState("y")

  def start: State = {
    println("GAME HAS STARTED")
    println("INSERT Player NAME:")
    GameStateContext.state
  }
}

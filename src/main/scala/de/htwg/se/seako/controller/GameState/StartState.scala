package de.htwg.se.seako.controller.GameState

case class StartState() extends State {

  override def handle(e: State): State =  start

  def start: State = {
    println("GAME HAS STARTED")
    println("Insert Player Name")
    GameStateContext.handle(WaitForInputState())
  }
}

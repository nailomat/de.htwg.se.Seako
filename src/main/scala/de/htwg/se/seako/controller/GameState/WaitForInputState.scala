package de.htwg.se.seako.controller.GameState

case class WaitForInputState() extends State {
  override def handle(e: State): State = GameStateContext.handle(WaitForInputState())
}

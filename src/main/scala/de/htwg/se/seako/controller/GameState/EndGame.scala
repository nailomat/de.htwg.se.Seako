package de.htwg.se.seako.controller.GameState

case class EndGame(gameSystem: GameSystem, stateRunner: StateRunner) extends State {

  def changeState = {
    stateRunner.off()
    gameSystem.previousState = this
  }
  def displayState={
  }

  stateRunner.on()

}

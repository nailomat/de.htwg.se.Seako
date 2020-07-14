package de.htwg.se.seako.controller.GameState

case class CreateGame (gameSystem: GameSystem, stateRunner: StateRunner) extends State {

  def changeState = {
    stateRunner.off()
    gameSystem.previousState = this
    gameSystem.currentState = gameSystem.newRound
  }
  def displayState={
  }

  stateRunner.on()

}

package de.htwg.se.seako.controller.GameState

case class Start(gameSystem: GameSystem, stateRunner: StateRunner) extends State {

  def changeState = {
    stateRunner.off()
    gameSystem.previousState = this
    gameSystem.currentState = gameSystem.insertPlayer
  }
  def displayState={
  }

  stateRunner.on()

}

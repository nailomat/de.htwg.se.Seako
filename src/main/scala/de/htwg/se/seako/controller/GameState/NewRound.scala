package de.htwg.se.seako.controller.GameState

case class NewRound(gameSystem: GameSystem, stateRunner: StateRunner) extends State {

  def changeState = {
    stateRunner.off()
    gameSystem.previousState = this
    gameSystem.currentState = gameSystem.playerTurn
  }
  def displayState={
  }

  stateRunner.on()

}

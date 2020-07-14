package de.htwg.se.seako.controller.GameState

case class InsertPlayer(gameSystem: GameSystem, stateRunner: StateRunner) extends State {

  def changeState = {
    stateRunner.off()
    gameSystem.previousState = this
    gameSystem.currentState = gameSystem.createGame
  }
  def displayState={
  }

  stateRunner.on()

}

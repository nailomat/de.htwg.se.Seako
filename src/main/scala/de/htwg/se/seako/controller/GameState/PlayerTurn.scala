package de.htwg.se.seako.controller.GameState

case class PlayerTurn(gameSystem: GameSystem, stateRunner: StateRunner) extends State{

  def changeState = {
    stateRunner.off()
    gameSystem.previousState = this
    gameSystem.currentState = gameSystem.enemyTurn
  }
  def displayState={
  }

  stateRunner.on()

}

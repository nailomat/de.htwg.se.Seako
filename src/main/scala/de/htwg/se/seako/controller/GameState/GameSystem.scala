package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.controller._

case class GameSystem(controller: Controller, input: String) {

  var initialState:StateRunner = new StartRunner()
  var currentState:State = new Start(this, initialState)
  var previousState:State= new Start(this, initialState)

  var start:State = new Start(this, initialState)
  var insertPlayer:State = new InsertPlayer(this, new InsertPlayerRunner(controller: Controller, input: String))
  var createGame:State = new CreateGame(this, CreateGameRunner(controller: Controller, input: String))
  var newRound:State = new NewRound(this, NewRoundRunner(controller: Controller))
  var playerTurn:State = new PlayerTurn (this, PlayerTurnRunner(controller: Controller, input: String))
  var enemyTurn:State = new EnemyTurn(this, EnemyTurnRunner(controller: Controller))
  var endGame:State = new EndGame(this, EndGameRunner())

  def changeState():Unit = {
    currentState.changeState()
  }
  def displayState():Unit = {
    currentState.displayState()
  }
}

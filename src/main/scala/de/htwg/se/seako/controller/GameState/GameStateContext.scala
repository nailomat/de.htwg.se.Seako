package de.htwg.se.seako.controller.GameState

object GameStateContext{

  var state : State = _

  def handle(e: State): Unit ={
    e match{
      case startState: StartState => state = startState.start
      case insertPlayerState: InsertPlayerState => state = insertPlayerState.insertPlayer
      case createGameState: CreateGameState => state = createGameState.createGame
      case newRoundState: NewRoundState => state = newRoundState.newRound
      case playerTurnState: PlayerTurnState => state = playerTurnState.playerTurn
      case enemyTurnState: EnemyTurnState => state = enemyTurnState.enemyTurn
      case endGameState: EndGameState => state = endGameState.endGame
    }
    state
  }
}

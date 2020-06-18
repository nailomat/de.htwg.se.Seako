package de.htwg.se.seako.controller

object GameStatus extends Enumeration {
  type GameStatus = Value
  val START, INSERTPLAYER, CREATEGAME, NEWROUND, PLAYERTURN, MOVE, FIGHT, ZOMBIETURN, ENDGAME = Value

  val map = Map[GameStatus, String](
    START -> "TYPE \"start\" TO START THE GAME",
    INSERTPLAYER -> "Insert Players",
    CREATEGAME -> "Creating Game",
    NEWROUND -> "Next Round - Spawning Zombies",
    PLAYERTURN -> "PLAYERS TURN",
    MOVE -> "PLAYER MOVE",
    FIGHT -> "PLAYER FIGHT",
    ZOMBIETURN -> "ZOMBIE MOVE",
    ENDGAME -> "Game finished"
  )

  def message(gameStatus: GameStatus): Unit = {
    map(gameStatus)
  }

}

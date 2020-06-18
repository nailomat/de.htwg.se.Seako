package de.htwg.se.seako.model

case class PlayerList(players: List[Player]) {

  def addPlayer(player: Player): PlayerList = {
    val newPlayers = players :+ player
    this.copy(players = newPlayers)
  }

  def removePlayer(player: Player): PlayerList = {
    val newPlayers = players.filterNot(players => players == player)
    this.copy(players = newPlayers)
  }

  def getCurrentPlayer: Player = {
    players.head
  }

  def nextPlayer: PlayerList = {
    val newPlayers = players.drop(1):+players.head
    this.copy(players = newPlayers)
  }



}

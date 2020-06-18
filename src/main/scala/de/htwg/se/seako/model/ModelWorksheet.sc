import de.htwg.se.seako.model.Player

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

val playerList2 = PlayerList(List[Player](Player("P1"),Player("P2"),Player("P3")))
playerList2.players
playerList2.addPlayer(Player("P4"))
playerList2.addPlayer(Player("P5"))
playerList2.removePlayer(Player("P1"))
playerList2.nextPlayer
playerList2.getCurrentPlayer


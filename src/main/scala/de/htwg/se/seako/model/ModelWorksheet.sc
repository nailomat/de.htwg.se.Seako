import de.htwg.se.seako.model.Player

case class PlayerList(players: List[Player]) {

  def addPlayer(player: Player): PlayerList = {
    if (!players.contains(player)) {
      val newPlayers = players :+ player
      this.copy(players = newPlayers)
    } else {
      println("Player already exists")
      this
    }
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
playerList2.addPlayer(Player("P1"))
playerList2.removePlayer(Player("P1"))
playerList2.nextPlayer
playerList2.getCurrentPlayer



val playerList1 = PlayerList(List[Player](Player("P1"),Player("P2"),Player("P3")))
playerList1.players
playerList1.addPlayer(Player("P4"))
playerList1.addPlayer(Player("P1"))
playerList1.removePlayer(Player("P1"))
playerList1.nextPlayer
playerList1.getCurrentPlayer
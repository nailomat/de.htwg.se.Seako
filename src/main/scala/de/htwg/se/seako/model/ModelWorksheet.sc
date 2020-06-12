import de.htwg.se.seako.model.{Cell, Finish, Fog, Player, Start, Terrain, Zombie}

case class Cell(players: List[Player], zombies: List[Zombie], terrain: Terrain, fog: Fog) {

  //  def isSet: Boolean = terrain.value != 0

  def addPlayer(player: Player): Cell = {
    val newPlayers = players :+ player
    val cell = this.copy(players = newPlayers)
    cell
  }

  override def toString: String = {
    var output = ""
    output += players.mkString(", ") + "\n"
    output += "\n"
    output += zombies.mkString(", ") + "\n"
    output
  }
}

val cell1 = Cell(Nil, Nil, Terrain(1), Fog(1))
cell1.addPlayer(Player("P1"))
cell1.addPlayer(Player("P2"))
cell1.copy(players = List(Player("P2")))
cell1
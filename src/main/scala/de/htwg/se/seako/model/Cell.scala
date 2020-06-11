package de.htwg.se.seako.model

case class Cell(players: List[Player], zombies: List[Zombie], terrain: Terrain, fog: Fog) {

  //  def isSet: Boolean = terrain.value != 0

  def addPlayer(player: Player): Cell = {
    val newPlayers = players :+ player
    this.copy(players = newPlayers)
  }

  def addZombie(zombie: Zombie): Cell = {
    val newZombies = zombies :+ zombie
    this.copy(zombies = newZombies)
  }

  override def toString: String = {
    var output = "";
    output += terrain.value;
    output += Console.GREEN + players.mkString(", ") + Console.RESET + "\n"
    output += Console.RED + zombies.mkString(", ") + Console.RESET
    output;
  }

  def topRow(): String = "⌈          ⌉"
  def playerRow(): String = players.mkString(", ")
  def zombieRow(): String = zombies.mkString(", ")
  def bottomRow(): String = "⌊          ⌋"
}

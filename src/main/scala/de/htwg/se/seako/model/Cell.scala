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

  def removeZombie(zombie: Zombie): Cell = {
    val newZombies = zombies
    if (zombies.nonEmpty) {

    }
    this.copy(zombies = newZombies)
  }

  def removePlayer(player: Player): Cell = {
    val newPlayers = players.filterNot(players => players == player)
    this.copy(players = newPlayers)
  }

  def topRow(): String = "⌈          ⌉"

  def playerRow(): String = players.mkString(", ")

  def zombieRow(): String = zombies.mkString(", ")

  def bottomRow(): String = "⌊          ⌋"

  override def toString: String = {
    var output = ""
    if (fog.value > 0) {
      output = "■"
    } else {
      output += terrain.value
      output += Console.GREEN + players.mkString(", ") + Console.RESET
      output += Console.RED + zombies.mkString(", ") + Console.RESET
    }
    output
  }
}

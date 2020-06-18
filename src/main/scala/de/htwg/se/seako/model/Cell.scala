package de.htwg.se.seako.model

case class Cell(players: List[Player], enemies: List[Enemies], terrain: Terrain, fog: Fog) {

  //  def isSet: Boolean = terrain.value != 0

  def addPlayer(player: Player): Cell = {
    val newPlayers = players :+ player
    this.copy(players = newPlayers)
  }


  def addEnemy(enemy: Enemies): Cell = {
    val newEnemies = enemies :+ enemy
    this.copy(enemies = newEnemies)
  }

  def removePlayer(player: Player): Cell = {
    val newPlayers = players.filterNot(players => players == player)
    this.copy(players = newPlayers)
  }

  def topRow(): String = "⌈          ⌉"

  def fogTopRow(): String = "⌈■■■■■■■■■■⌉"

  def playerRow(): String = players.mkString(", ")

  def fogPlayerRow(): String = "|■■■■■■■■■■|"

  def zombieRow(): String = enemies.mkString(", ")

  def fogZombieRow(): String = "|■■■■■■■■■■|"

  def fogBottomRow(): String = "⌊■■■■■■■■■■⌋"

  def bottomRow(): String = "⌊          ⌋"

  override def toString: String = {
    var output = ""
    if (fog.value > 0 && players.isEmpty) {
      output = "■"
    } else {
      output += terrain.value
      output += Console.GREEN + players.mkString(", ") + Console.RESET
      output += Console.RED + enemies.mkString(", ") + Console.RESET
    }
    output
  }
}

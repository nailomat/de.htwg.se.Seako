package de.htwg.se.seako.model

case class Cell(players: List[Player], enemies: Enemies, terrain: Terrain, fog: Fog) {

  //  def isSet: Boolean = terrain.value != 0

  def addPlayer(player: Player): Cell = {
    val newPlayers = players :+ player
    this.copy(players = newPlayers)
  }


  def addEnemy(enemy: String): Cell = {
    var tempEnemy = enemies
    enemy match {
      case "zombie" =>  tempEnemy = enemies.addZombie(Zombie())
      case "mutant" => tempEnemy = enemies.addMutant(Mutant())
      case "boss" => tempEnemy= enemies.addBoss(Boss())
    }
    this.copy(enemies = tempEnemy)
  }

  def removePlayer(player: Player): Cell = {
    val newPlayers = players.filterNot(players => players == player)
    this.copy(players = newPlayers)
  }

  def topRow(): String = "⌈          ⌉"

  def fogTopRow(): String = "⌈■■■■■■■■■■⌉"

  def playerRow(): String = players.mkString(", ")

  def fogPlayerRow(): String = "|■■■■■■■■■■|"

  def zombieRow(): String = enemies.zombies.mkString(", ") + enemies.mutants.mkString(", ") + enemies.bosses.mkString(", ")

  def fogZombieRow(): String = "|■■■■■■■■■■|"

  def fogBottomRow(): String = "⌊■■■■■■■■■■⌋"

  def bottomRow(): String = "⌊          ⌋"

}

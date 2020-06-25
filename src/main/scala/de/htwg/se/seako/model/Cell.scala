package de.htwg.se.seako.model

case class Cell(players: List[Player], enemies: Enemies, terrain: Terrain, fog: Fog) {

  //  def isSet: Boolean = terrain.value != 0

  def addEnemy(enemy: String): Cell = {
    var tempEnemy = enemies
    enemy match {
      case "zombie" =>  tempEnemy = enemies.addEnemy(enemy)
      case "mutant" => tempEnemy = enemies.addEnemy(enemy)
      case "boss" => tempEnemy= enemies.addEnemy(enemy)
    }
    this.copy(enemies = tempEnemy)
  }

  def removeEnemy(enemy: String): Cell = {
    var tempEnemy = enemies
    enemy match {
      case "zombie" =>  tempEnemy = enemies.removeEnemy(enemy)
      case "mutant" => tempEnemy = enemies.removeEnemy(enemy)
      case "boss" => tempEnemy= enemies.removeEnemy(enemy)
    }
    this.copy(enemies = tempEnemy)
  }


  def addPlayer(player: Player): Cell = {
    val newPlayers = players :+ player
    this.copy(players = newPlayers)
  }



  def removePlayer(player: Player): Cell = {
    val newPlayers = players.filterNot(players => players == player)
    this.copy(players = newPlayers)
  }

  def topRow(): String = "⌈          ⌉"

  def fogTopRow(): String = "⌈■■■■■■■■■■⌉"

  def playerRow(): String = players.mkString(", ")

  def fogPlayerRow(): String = "|■■■■■■■■■■|"

  def enemyRow(): String = enemies.enemies.mkString(", ")

  def fogZombieRow(): String = "|■■■■■■■■■■|"

  def fogBottomRow(): String = "⌊■■■■■■■■■■⌋"

  def bottomRow(): String = "⌊          ⌋"

}

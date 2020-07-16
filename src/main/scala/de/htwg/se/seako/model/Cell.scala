package de.htwg.se.seako.model

case class Cell(players: List[Player], enemies: Enemies, terrain: Terrain, fog: Fog) {

  //  def isSet: Boolean = terrain.value != 0

  def addEnemy(enemy: String): Cell = {
    var tempEnemy = enemies
    enemy match {
      case "zombie" => tempEnemy = enemies.addEnemy(enemy)
      case "mutant" => tempEnemy = enemies.addEnemy(enemy)
      case "boss" => tempEnemy = enemies.addEnemy(enemy)
    }
    this.copy(enemies = tempEnemy)
  }

  def removeEnemy(enemy: Option[String]): Cell = {
    var tempEnemy = enemies
    enemy match {
      case Some("zombie") => tempEnemy = enemies.removeEnemy(enemy)
      case Some("mutant") => tempEnemy = enemies.removeEnemy(enemy)
      case Some("boss") => tempEnemy = enemies.removeEnemy(enemy)
      case None =>
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


  def attackEnemy(player: Player, power: Int): Option[String] = {
    var TheEnemy = Some("")
    val newEnemy = enemies
    val attack = newEnemy.amountOfEnemys()
    attack match{
      case (attack._1, attack._2, attack._3) if attack._1 > 0 =>
        if (power > 1) {
          TheEnemy = Some("zombie")
          TheEnemy
        } else {
          None
        }
      case (attack._1, attack._2, attack._3) if attack._2 > 0 =>
        if (power > 2) {
          TheEnemy = Some("mutant")
          TheEnemy
        } else {
          None
        }
      case (attack._1, attack._2, attack._3) if attack._3 > 0 =>
        if (power > 4) {
          TheEnemy = Some("boss")
          TheEnemy
        } else {
          None
        }
      case (0, 0, 0) =>
        None
    }


  }

  def setFogValue(value: Int): Cell = {
    val newFog = Fog(value)
    this.copy(fog = newFog)
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

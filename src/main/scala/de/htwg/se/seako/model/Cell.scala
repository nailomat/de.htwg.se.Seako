package de.htwg.se.seako.model

import de.htwg.se.seako.model

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



  def attackEnemy (player: Player, power : Int): Cell = {
    val newEnemy = enemies
    val attack = newEnemy.amountOfEnemys()
    if(attack._1 > 0 && power > 1){
        removeEnemy("zombie")

        //nochmal
      }
    else if(attack._2 > 0  && power > 2){
        removeEnemy("mutant")
      }
    else if (attack._3 > 0 && power > 4) {
        removeEnemy("boss")
      }
    val oldEnemy = enemies
    this.copy(enemies = oldEnemy)
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

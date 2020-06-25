package de.htwg.se.seako.model

trait Enemy{
  def isAlive: Boolean
  def getHealth: Integer
  def getAttack: Integer
  def addEnemy: Unit
}

object Enemy {

  def wichEnemy(enemy: String): Unit = {
    enemy match {
      case "zombie" =>  Zombie()
      case "mutant" => Mutant()
      case "boss" => Boss()
    }
  }


}

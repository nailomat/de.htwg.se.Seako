package de.htwg.se.seako.model

trait Enemy{
  def isAlive: Boolean
  def getHealth: Integer
  def getAttack: Integer
}

object Enemy {

  def apply(kind: String) = kind match {
      case "zombie" => new Zombie()
      case "mutant" => new  Mutant()
      case "boss" => new Boss()
  }
}

case class Enemies(enemies: List[Enemy]) {


//  def getValue: Integer = {
//    var value = 0
//    value = value + (zombies.length * 1)
//    value = value + (mutants.length * 2)
//    value = value + (bosses.length * 5)
//    value
//  }

  def addEnemy (enemy : String): Enemies = {
    var tmpEnemy = enemies
    val newEnemy = Enemy(enemy)
    enemy match {
      case "zombie" => tmpEnemy = enemies :+ newEnemy.asInstanceOf[Zombie]
      case "mutant" => tmpEnemy = enemies :+ newEnemy.asInstanceOf[Mutant]
      case "boss" => tmpEnemy = enemies :+ newEnemy.asInstanceOf[Boss]
    }
    this.copy(enemies = tmpEnemy)
  }

}
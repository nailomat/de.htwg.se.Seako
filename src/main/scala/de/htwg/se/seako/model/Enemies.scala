package de.htwg.se.seako.model

import scala.collection.mutable.ListBuffer

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

  def removeEnemy (enemy: String): Enemies = {
    var tmpEnemy = enemies
    var x = 0
      enemy match {
        case "zombie"=> x = tmpEnemy.indexWhere(p => p.isInstanceOf[Zombie])
        case "mutant"=> x = tmpEnemy.indexWhere(p => p.isInstanceOf[Mutant])
        case "boss"=> x = tmpEnemy.indexWhere(p => p.isInstanceOf[Boss])
      }
    if(x > 0) {
     tmpEnemy = tmpEnemy.drop(x)
    }
    this.copy(enemies = tmpEnemy)
  }


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
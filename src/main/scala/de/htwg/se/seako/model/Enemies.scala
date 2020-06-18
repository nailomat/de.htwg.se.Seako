package de.htwg.se.seako.model

trait Enemy{
  def isAlive: Boolean
  def getHealth: Integer
  def getAttack: Integer
}

case class Enemies(zombies: List[Zombie] = Nil,mutants: List[Mutant]= Nil,bosses: List[Boss]= Nil) {


  def getValue: Integer = {
    var value = getValue
    zombies.foreach(Zombie => value = value + Zombie.getAttack)
    mutants.foreach(Mutant => value = value + Mutant.getAttack)
    bosses.foreach(Boss => value = value + Boss.getAttack)
    value
  }

  def addZombie(zombie: Zombie): Enemies = {
    val newZombies = zombies :+ zombie
    this.copy(zombies = newZombies)
  }

  def addMutant(mutant: Mutant): Enemies = {
    val newMutants = mutants :+ mutant
    this.copy(mutants = newMutants)
  }

  def addBoss(boss: Boss): Enemies = {
    val newBoss = bosses :+ boss
    this.copy(bosses = newBoss)
  }

}

package de.htwg.se.seako.model

trait Enemy{
  def isAlive: Boolean
  def getHealth: Integer
  def getAttack: Integer
}

case class Enemies(zombies: List[Zombie] = Nil,mutants: List[Mutant]= Nil,bosses: List[Boss]= Nil) {


  def getValue: Integer = {
    var value = 0
    value = value + (zombies.length * 1)
    value = value + (mutants.length * 2)
    value = value + (bosses.length * 5)
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

package de.htwg.se.seako.model

trait Enemy{
  def isAlive: Boolean
  def getHealth: Integer
  def getAttack: Integer
}

case class Enemies(zombies: List[Zombie],mutants: List[Mutant],bosses: List[Boss]) {


  def getValue: Integer = {
    var value : Integer = 0
    Enemies match {
      case zombies.length > 0 => zombies.foreach(Zombie => value = value + Zombie.getAttack)
      case mutants.length > 0 => mutants.foreach(Mutant => value = value + Mutant.getAttack)
      case bosses.length > 0 => bosses.foreach(Boss => value = value + Boss.getAttack)
    }
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

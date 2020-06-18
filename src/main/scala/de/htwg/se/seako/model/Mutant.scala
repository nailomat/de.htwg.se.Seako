package de.htwg.se.seako.model


case class Mutant(health: Int = 3, attack: Int = 2) {
  def isAlive: Boolean = health > 0
  def hasAttack: Boolean = attack > 0

  override def toString: String = "M"

}

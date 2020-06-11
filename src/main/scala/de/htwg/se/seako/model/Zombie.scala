package de.htwg.se.seako.model

case class Zombie(health: Int, attack: Int) {
  def isAlive: Boolean = health > 0
  def hasAttack: Boolean = attack > 0

  override def toString: String = "X"

}

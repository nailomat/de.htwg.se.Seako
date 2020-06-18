package de.htwg.se.seako.model

case class Zombie(health: Int = 1, attack: Int = 1) {
  def isAlive: Boolean = health > 0
  def hasAttack: Boolean = attack > 0

  override def toString: String = "Z"

}

package de.htwg.se.seako.model

case class Boss(health: Int = 5, attack: Int = 5) {
  def isAlive: Boolean = health > 0
  def hasAttack: Boolean = attack > 0

  override def toString: String = "B"

}
package de.htwg.se.seako.model

case class Mutant(health: Int = 3, attack: Int = 2) extends Enemy {

  override def isAlive: Boolean = health > 0

  override def getAttack: Integer = attack

  override def getHealth: Integer = health

  override def toString: String = "M"

}

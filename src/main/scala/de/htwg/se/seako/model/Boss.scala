package de.htwg.se.seako.model

case class Boss(health: Int = 5, attack: Int = 5) extends Enemy {

  override def isAlive: Boolean = health > 0

  override def getAttack: Integer = attack

  override def getHealth: Integer = health

  override def toString: String = "B"

}
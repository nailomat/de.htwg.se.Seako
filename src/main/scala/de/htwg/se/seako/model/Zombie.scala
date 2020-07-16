package de.htwg.se.seako.model

private class Zombie(health: Int = 2, attack: Int = 1) extends Enemy {

  override def toString: String = "Z"

  override def isAlive: Boolean = health > 0

  override def getAttack: Integer = attack

  override def getHealth: Integer = health

}

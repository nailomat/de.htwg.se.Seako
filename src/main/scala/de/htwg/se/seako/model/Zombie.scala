package de.htwg.se.seako.model

case class Zombie(value: Int) {
  def isAlive: Boolean = value > 0
}

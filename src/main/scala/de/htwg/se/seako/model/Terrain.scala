package de.htwg.se.seako.model

case class Terrain(value:Int) {
  def isSet: Boolean = value != 0
}

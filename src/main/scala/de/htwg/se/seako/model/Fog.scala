package de.htwg.se.seako.model

case class Fog(value:Integer) {
  def isSet:Boolean = value > 0
}

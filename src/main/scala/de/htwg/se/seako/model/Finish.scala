package de.htwg.se.seako.model

case class Finish(value:Int) {
  def isSet: Boolean = value != 0
}

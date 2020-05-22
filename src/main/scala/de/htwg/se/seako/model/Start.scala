package de.htwg.se.seako.model

case class Start(value:Int) {
  def isSet: Boolean = value != 0
}

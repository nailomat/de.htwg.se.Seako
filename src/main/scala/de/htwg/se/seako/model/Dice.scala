package de.htwg.se.seako.model

case class Dice(value: Integer) {
  val min = 1
  val max = 6

  def rolldice: Integer = value { min + scala.util.Random.nextInt(max - min) }

}

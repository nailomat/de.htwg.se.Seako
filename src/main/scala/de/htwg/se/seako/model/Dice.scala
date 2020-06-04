package de.htwg.se.seako.model

import scala.util.Random


case class Dice() {
  val min = 1
  val max = 6
  var value = 1

  def rolldice: Integer = {
    value = min + Random.nextInt((max - min) + 1)
    value
  }
}

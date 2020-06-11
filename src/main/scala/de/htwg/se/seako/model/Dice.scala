package de.htwg.se.seako.model

import scala.util.Random


case class Dice(min: Integer = 1, max: Integer = 6) {
  def rolldice: Integer = {
    val value = min + Random.nextInt(max)
    value
  }
}

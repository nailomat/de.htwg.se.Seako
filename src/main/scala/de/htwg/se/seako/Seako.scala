package de.htwg.se.seako

import de.htwg.se.seako.model.Player

object Seako {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
    println("Seako")
  }
}

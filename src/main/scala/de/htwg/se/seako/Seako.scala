package de.htwg.se.seako

import de.htwg.se.seako.model.{Cell, Grid, Player}

object Seako {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
    println("Seako")
    val playingField = new Grid[Cell](2,Cell(1))
    println(playingField.cell(0,1))
    val newPlayingField = playingField.replaceCell(0,1,Cell(2))
    println(newPlayingField.cell(0,1))

  }
}

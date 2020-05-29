package de.htwg.se.seako

import de.htwg.se.seako.aview.Tui
import de.htwg.se.seako.model.{Cell, Grid, Player}

object Seako {
  def main(args: Array[String]): Unit = {
    var tui = new Tui()
//    val playingField = new Grid[Cell](2,Cell(1))
//    println(playingField.cell(0,1))
//    val newPlayingField = playingField.replaceCell(0,1,Cell(2))
//    println(newPlayingField.cell(0,1))
    var input: String = ""
    if (args.length > 0) input = args(0)
    if (!input.isEmpty) tui.processInputLine(input)
    else do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}

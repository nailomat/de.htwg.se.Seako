package de.htwg.se.seako

import de.htwg.se.seako.aview.Tui
import de.htwg.se.seako.model.{Cell, Grid, Player}

object Seako {
  def main(args: Array[String]): Unit = {
    var grid = new Grid[Cell](1, Cell(0))
    val tui = new Tui()
    var input: String = ""
    if (args.length > 0) input = args(0)
    do {
      grid = tui.processInputLine(input, grid)
      println("Grid : \n" + grid.toString)
      input = readLine()
    } while (input != "q")
  }
}

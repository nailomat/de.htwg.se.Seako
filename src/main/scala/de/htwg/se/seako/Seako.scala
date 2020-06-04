package de.htwg.se.seako

import de.htwg.se.seako.aview.Tui
import de.htwg.se.seako.model.{Cell, Grid, Player, Terrain, Zombie}

object Seako {
  def main(args: Array[String]): Unit = {
    val cell = new Cell(List[Player](), List[Zombie](Zombie(1,0)),Terrain(1))
    var grid = new Grid[Cell](1, cell)
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

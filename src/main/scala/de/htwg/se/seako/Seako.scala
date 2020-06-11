package de.htwg.se.seako

import de.htwg.se.seako.aview.Tui
import de.htwg.se.seako.controller.Controller
import de.htwg.se.seako.model.{Cell, Fog, Grid, Player, Terrain, Zombie}

object Seako {
  def main(args: Array[String]): Unit = {
    val controller = new Controller(grid = Grid[Cell])
    val tui = new Tui(controller)
//    val cell = new Cell(List[Player](), List[Zombie](Zombie(1,0)),Terrain(1), Fog(0))
//    var grid = new Grid[Cell](1, cell)
//    val tui = new Tui()
    var input: String = ""
    if (args.length > 0) input = args(0)
    do {
      tui.processInputLine(input)
      input = readLine()
    } while (input != "q")
  }

}

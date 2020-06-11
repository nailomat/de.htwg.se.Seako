package de.htwg.se.seako

import de.htwg.se.seako.aview.Tui
import de.htwg.se.seako.controller.Controller
import de.htwg.se.seako.model.{Cell, Fog, Grid, Player, Terrain, Zombie}

object Seako {
  def main(args: Array[String]): Unit = {
    val cell = Cell(List[Player](Player("P1")), Nil,Terrain(0), Fog(1))
    val controller = new Controller(new Grid[Cell](5, cell))
    val tui = new Tui(controller)
    controller.notifyObservers()
    var input: String = ""

    do {
      input = readline()
      tui.processInputLine(input)
    } while (input != "q")
  }

}

package de.htwg.se.seako

import de.htwg.se.seako.aview.Tui
import de.htwg.se.seako.controller.Controller
import de.htwg.se.seako.model._

object Seako {
  def main(args: Array[String]): Unit = {
    val cell = Cell(Nil, Nil,Terrain(0), Fog(1))
    val controller = new Controller(new Grid[Cell](0, cell),PlayerList(Nil))
    val tui = new Tui(controller)
    controller.notifyObservers()
    var input: String = ""

    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }

}

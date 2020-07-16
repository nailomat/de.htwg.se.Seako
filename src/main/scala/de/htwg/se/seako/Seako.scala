package de.htwg.se.seako

import de.htwg.se.seako.aview.Tui
import de.htwg.se.seako.aview.gui.SwingGui
import de.htwg.se.seako.controller.GameState.GameStateContext
import de.htwg.se.seako.controller.{CellChange, Controller, GameStatus}
import de.htwg.se.seako.model._

object Seako {
  val cell = Cell(Nil, Enemies(Nil),Terrain(0), Fog(1))
  val controller = new Controller(new Grid[Cell](1, cell),PlayerList(Nil), new GameStateContext)
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)
//  val gui = new StartPanel(controller)
  controller.publish(new CellChange)
  def main(args: Array[String]): Unit = {
    //controller.notifyObservers()
    var input: String = ""

    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }

}

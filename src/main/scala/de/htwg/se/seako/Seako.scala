package de.htwg.se.seako

import de.htwg.se.seako.aview.Tui
import de.htwg.se.seako.aview.gui.StartFrame
import de.htwg.se.seako.controller.{CellChange, Controller}
import de.htwg.se.seako.model._

object Seako {
  val cell = Cell(Nil, Enemies(Nil),Terrain(0), Fog(1))
  val controller = new Controller(new Grid[Cell](0, cell),PlayerList(Nil))
  val tui = new Tui(controller)
//  val gui = new SwingGui(controller)
  val gui = new StartFrame(controller)
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

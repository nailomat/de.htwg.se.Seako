package de.htwg.se.seako.aview

import de.htwg.se.seako.controller.Controller
import de.htwg.se.seako.model.{Cell, Finish, Fog, Grid, Player, Start, Terrain, Zombie}
import de.htwg.se.seako.util.Observer

class Tui(controller: Controller) extends Observer{

  controller.add(this)
  def processInputLine(input: String): Unit= {
    input match {
      case "q" =>
      case "t" =>controller.createEmptyGrid(5)
      case "small" => controller.createEmptyGrid(5)
      case "medium" => controller.createEmptyGrid(10)
      case "big" => controller.createEmptyGrid(20)
      case _ => input.toList.filter(c => c != ' ').map(c => c.toString) match {
        case command :: row :: column :: Nil =>
          if (command.equals("addZombie")) {
            controller.addZombie(row.toInt, column.toInt)
          }

        case command :: row :: column :: value ::Nil =>
      }

      def validateLongString(input: String): Unit = {

      }

    }

  }

  override def update(): Unit = println(controller.gridToString)
}

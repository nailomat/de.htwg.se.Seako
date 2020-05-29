package de.htwg.se.seako.aview

import de.htwg.se.seako.model.{Grid, Cell, Finish, Player, Start, Terrain, Zombie}

class Tui {

  def processInputLine(input: String): Unit = {
    input match {
      case "q" =>
      case "n" => new Grid[Cell](10,Cell(0))
      case "small" => new Grid[Cell](5,Cell(0))
      case "medium" => new Grid[Cell](10,Cell(0))
      case "big" => new Grid[Cell](20,Cell(0))
      case _ => {
        input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
          case _ => println("unknown command")
        }
      }
    }

  }


}

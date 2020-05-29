package de.htwg.se.seako.aview

import de.htwg.se.seako.model.{Grid, Cell, Finish, Player, Start, Terrain, Zombie}

class Tui {

  def processInputLine(input: String, grid: Grid):Grid = {
    input match {
      case "q" => grid
      case "n" => new Grid(16)
    }

  }


}

package de.htwg.se.seako.aview

import de.htwg.se.seako.model.{Cell, Finish, Fog, Grid, Player, Start, Terrain, Zombie}

class Tui {

  def processInputLine(input: String, grid:Grid[Cell]): Grid[Cell] = {
    input match {
      case "q" =>grid
      case "t" => new Grid[Cell](1, Cell(List[Player](), List[Zombie](), Terrain(0), Fog(0)))
      case "n" => new Grid[Cell](10,Cell(List[Player](),List[Zombie](), Terrain(0), Fog(0)))
      case "small" => new Grid[Cell](5,Cell(List[Player](),List[Zombie](), Terrain(0), Fog(1)))
      case "medium" => new Grid[Cell](10,Cell(List[Player](),List[Zombie](), Terrain(0), Fog(1)))
      case "big" => new Grid[Cell](20,Cell(List[Player](),List[Zombie](), Terrain(0), Fog(1)))
      case _ => {
        input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
          case row :: column :: value :: Nil => grid.replaceCell(row, column, Cell(List[Player](Player("P"+value)), List[Zombie](), Terrain(0),Fog(0)))
          case _ => grid
        }
      }
    }

  }


}

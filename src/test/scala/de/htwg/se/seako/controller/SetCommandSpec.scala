package de.htwg.se.seako.controller

import de.htwg.se.seako.model._
import org.scalatest.{Matchers, WordSpec}

class SetCommandSpec extends WordSpec with Matchers{
  val controller = new Controller(new Grid[Cell](5, Cell(Nil, Enemies(Nil), Terrain(0), Fog(0))), PlayerList(List[Player](Player("1"),Player("2"))))
  val setCommand = new SetCommand(0,0, Cell(Nil, Enemies(Nil), Terrain(0),Fog(0)), controller)
  val tmpCell = controller.grid.cell(0,0)
  "SetCommand" when {
    "doing a doStep" in {
      setCommand.doStep() should be ()
    }
    "doing a undoStep" in {
      controller.addPlayer(0,0,"1")
      setCommand.undoStep()
      controller.cell(0,0).players.contains(Player("1")) should be (false)
    }
    "doing a redoStep" in {
      setCommand.redoStep() should be ()
    }
  }

}

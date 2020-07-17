package de.htwg.se.seako.aview.gui

import de.htwg.se.seako.controller.Controller
import de.htwg.se.seako.model._
import org.scalatest.{Matchers, WordSpec}

class CellPanelSpec extends WordSpec with Matchers {

  "A CellPanel" when {
    "has a cell" should {
      "have a cell type fog" in {
        val controller = new Controller(new Grid[Cell](5, Cell(Nil, Enemies(Nil), Terrain(0), Fog(1))), PlayerList(Nil))
        val cellPanel = new CellPanel(0,0,controller)
        val cell = cellPanel.myCell
        cellPanel.myCellType should be ("fog")
      }
      "have a cell type player" in {
        val controller = new Controller(new Grid[Cell](5, Cell(List[Player](Player("1")), Enemies(Nil), Terrain(0), Fog(1))), PlayerList(Nil))
        val cellPanel = new CellPanel(0,0,controller)
        val cell = cellPanel.myCell
        cellPanel.myCellType should be ("player")
      }
      "have a cell type terrain" in {
        val controller = new Controller(new Grid[Cell](5, Cell(Nil, Enemies(Nil), Terrain(1), Fog(0))), PlayerList(Nil))
        val cellPanel = new CellPanel(0,0,controller)
        val cell = cellPanel.myCell
        cellPanel.myCellType.nonEmpty should be (true)
      }
    }
  }
}

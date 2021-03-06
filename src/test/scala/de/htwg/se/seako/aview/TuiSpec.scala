package de.htwg.se.seako.aview

import de.htwg.se.seako.controller.{Controller, GameStatus}
import de.htwg.se.seako.model._
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends WordSpec with Matchers {
  val controller = new Controller(new Grid[Cell](5, Cell(Nil, Enemies(Nil), Terrain(0), Fog(1))), PlayerList(Nil))
  "A Seako Tui" should {
    val tui = new Tui(controller)
    "create and small empty Seako on input 'small'" in {
      controller.setGameStatus(GameStatus.CREATEGAME)
      tui.processInputLine("small")
      controller.grid should be(new Grid[Cell](5, Cell(Nil, Enemies(Nil), Terrain(0), Fog(1))))
    }
    "create and medium empty Seako on input 'medium'" in {
      controller.setGameStatus(GameStatus.CREATEGAME)
      tui.processInputLine("medium")
      controller.grid should be(new Grid[Cell](10, Cell(Nil, Enemies(Nil), Terrain(0), Fog(1))))
    }
    "create and big empty Seako on input 'big'" in {
      controller.setGameStatus(GameStatus.CREATEGAME)
      tui.processInputLine("big")
      controller.gridSize should be(20)
    }

    "activate a command on input 'addZombie'" in {
      tui.processInputLine("addZombie 0 0")
      controller.grid.cell(0, 0).enemies.amountOfEnemys()._1 should be(1)
    }
    "add Player on input 'addPlayer 0 0 P1' and remove it on input 'removePlayer P1" in {
      tui.processInputLine("small")
      tui.processInputLine("addPlayer 0 0 P1")
      //      controller.grid.cell(0,0) should be (Cell(List[Player](Player("P1")),Nil, Terrain(0), Fog(1)))
      tui.processInputLine("removePlayer 0 0 P1")
      //      controller.grid.cell(0,0) should be (Cell(Nil, Nil, Terrain(0), Fog(1)))
    }
    "an not accepted input would be everything else" in {
      tui.processInputLine("_") should be()
    }
    "an empty input would be everything else" in {
      tui.processInputLine("") should be()
    }
    "exit on input 'q'" in {
      tui.processInputLine("q") should be()
    }
  }

}

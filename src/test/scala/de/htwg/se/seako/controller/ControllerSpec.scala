package de.htwg.se.seako.controller

import de.htwg.se.seako.controller.GameStatus.GameStatus
import de.htwg.se.seako.model._
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers{

  val controller = new Controller(new Grid[Cell](5, Cell(Nil, Enemies(Nil), Terrain(0), Fog(0))), PlayerList(List[Player](Player("1"),Player("2"))))
  var gameStatus: GameStatus = GameStatus.START
  "A Controller" when {
    "empty" should {
      val controller = new Controller(new Grid[Cell](5, Cell(Nil, Enemies(Nil), Terrain(0), Fog(0))), PlayerList(Nil))
      "handle undo/redo of solving a grid correctly" in {
        controller.undo should be (())
        controller.redo should be (())
      }
    }
    "Set GameStatus" in {
      controller.setGameStatus(GameStatus.CREATEGAME)
      controller.gameStatus should be (GameStatus.CREATEGAME)
    }
    "Start Game" in {
      controller.startGame()
      controller.gameStatus should be (GameStatus.INSERTPLAYER)
    }
    "Add Player to Playerlist" in {
      controller.addPlayerList("3")
      controller.playerList.players.contains(Player("3")) should be (true)
    }
    "Next Player to PlayerList" in {
      controller.nextPlayer()
      controller.playerList.players.head should be (Player("2"))
    }
    "Create EmptyGrid Size 5" in {
      controller.createEmptyGrid(5)
      controller.gridSize should be (5)
    }
    "Add Player to Cell" in {
      controller.addPlayer(0,0,"1")
      controller.cell(0,0).players.contains(Player("1")) should be (true)
    }
    "Remove Player to Cell" in {
      controller.addPlayer(0,0,"1")
      controller.removePlayer(0,0,"1")
      controller.cell(0,0).players.contains(Player("1")) should be (false)
    }
    "Add Enemy to Cell" in {
      controller.addEnemy(0,0,"zombie")
      controller.cell(0,0).enemies.amountOfEnemys()._1 should be (1)
    }
    "Remove Enemy to Cell" in {
      controller.removeEnemy(0,0, Option("zombie"))
      controller.cell(0, 0).enemies.amountOfEnemys()._1 should be (0)
    }
    "Move Player to Cell" in {
      controller.addPlayer(0,0,"1")
      controller.movePlayer("down")
      controller.cell(0,1).players.contains(Player("1")) should be (false)
    }
    "Gives amount of Enemies" in {
      controller.addEnemy(0,0,"zombie")
      controller.amountEnemies()._1 should be (0)
    }
    "Attack Enemy "in {
      controller.attackEnemy() should be (new Dice)
    }
    "to String" in {
      controller.createEmptyGrid(0)
      controller.gridToString should be ("\n")
    }
    "set Cell" in {
      controller.createEmptyGrid(1)
      controller.setCell(0, 0, Cell(Nil,Enemies(Nil),Terrain(1), Fog(0)))
      controller.cell(0,0).fog.value should be (0)
    }
    "various Inputs" in {
      controller.validateLongString("123")
      controller.playerList.players.contains(Player("123")) should be (true)
      controller.validateLongString("y")
      controller.validateLongString("n")
      controller.validateLongString("small")
      controller.validateLongString("medium")
      controller.validateLongString("big")
      controller.validateLongString("np")
      controller.validateLongString("attack")
      controller.validateLongString("move down")
      controller.validateLongString("123412 123123")
      controller.validateLongString("addCurrentPlayer 0 0")
      controller.validateLongString("addZombie 0 0")
      controller.validateLongString("addMutant 0 0")
      controller.validateLongString("addBoss 0 0")
      controller.validateLongString("removeZombie 0 0")
      controller.validateLongString("removeMutant 0 0")
      controller.validateLongString("removeBoss 0 0")
      controller.validateLongString("09 123 123")
      controller.validateLongString("addPlayer 0 0 1")
      controller.validateLongString("removePlayer 0 0 1")
      controller.validateLongString("123 123 123 123")
      controller.validateLongString("1231 1242 512 52 52") should be ()
    }
    "medium size" in {
      controller.setGameStatus(GameStatus.CREATEGAME)
      controller.validateLongString("medium")
      controller.gameStatus should be (GameStatus.NEWROUND)
    }
    "big size" in {
      controller.setGameStatus(GameStatus.CREATEGAME)
      controller.validateLongString("big")
      controller.gameStatus should be (GameStatus.NEWROUND)
    }
    "unknown size" in {
      controller.setGameStatus(GameStatus.CREATEGAME)
      controller.validateLongString("1231245") should be ()
    }
  }
}

package de.htwg.se.seako.model

import org.scalatest.{Matchers, WordSpec}

class CellSpec extends WordSpec with Matchers{
  "A Cell" when {
    "not set to any value " should {
      val emptyCell = Cell(Nil, Nil, Terrain(0), Fog(0))
      "have value Nil or 0" in {
        emptyCell.players should be (Nil)
        emptyCell.enemies should be (Nil)
        emptyCell.terrain should be (Terrain(0))
        emptyCell.fog should be (Fog(0))
      }
      "if added a Player and then removed again" in {
        val onePlayerCell = emptyCell.addPlayer(Player("TestPlayer"))
        onePlayerCell.players should be(List(Player("TestPlayer")))
        val removePlayerCell = onePlayerCell.removePlayer(Player("TestPlayer"))
        removePlayerCell.players should be (Nil)
      }

      "if added a Enemy" in {
        val oneZombieCell = emptyCell.addEnemy(Enemies(List(Zombie()),Nil,Nil))
        oneZombieCell.enemies should be (List(Zombie(1,1)))
      }
      "if printed out" in {
        emptyCell.topRow() should be ("⌈          ⌉")
        emptyCell.fogTopRow() should be ("⌈■■■■■■■■■■⌉")
        emptyCell.playerRow() should be ("")
        emptyCell.enemyRow() should be ("")
        emptyCell.fogPlayerRow() should be ("|■■■■■■■■■■|")
        emptyCell.fogZombieRow() should be ("|■■■■■■■■■■|")
        emptyCell.bottomRow() should be ("⌊          ⌋")
        emptyCell.fogBottomRow() should be ("⌊■■■■■■■■■■⌋")
        emptyCell.toString should be ("0\u001B[32m\u001B[0m\u001B[31m\u001B[0m")
      }
    }
    "the Fog value is Set" should{
      val fogCell = Cell(Nil, Nil, Terrain(0), Fog(1))
      "the User see nothing" in{
        fogCell.toString should be ("■")
      }
    }
  }
}

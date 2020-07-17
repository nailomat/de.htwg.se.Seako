package de.htwg.se.seako.model

import de.htwg.se.seako.model
import org.scalatest.{Matchers, WordSpec}

class CellSpec extends WordSpec with Matchers{
  "A Cell" when {
    "not set to any value " should {
      val emptyCell = Cell(Nil, Enemies(Nil), Terrain(0), Fog(0))
      "have value Nil or 0" in {
        emptyCell.players should be (Nil)
        emptyCell.enemies should be (Enemies(List()))
        emptyCell.terrain should be (Terrain(0))
        emptyCell.fog should be (Fog(0))
      }
      "if added a Player and then removed again" in {
        val onePlayerCell = emptyCell.addPlayer(Player("TestPlayer"))
        onePlayerCell.players should be(List(Player("TestPlayer")))
        val removePlayerCell = onePlayerCell.removePlayer(Player("TestPlayer"))
        removePlayerCell.players should be (Nil)
      }

      "if added a Zombie" in {
        val oneZombieCell = emptyCell.addEnemy("zombie")
        assert(oneZombieCell.enemies.isInstanceOf[Enemies])
        oneZombieCell.removeEnemy(Some("zombie"))
        assert(oneZombieCell.enemies.isInstanceOf[Enemies])
      }
      "if added a Mutant" in {
        val oneMutantCell = emptyCell.addEnemy("mutant")
        assert(oneMutantCell.enemies.isInstanceOf[Enemies])
        oneMutantCell.removeEnemy(Some("mutant"))
        assert(oneMutantCell.enemies.isInstanceOf[Enemies])
      }
      "if added a Boss" in {
        val oneBossCell = emptyCell.addEnemy("boss")
        assert(oneBossCell.enemies.isInstanceOf[Enemies])
        oneBossCell.removeEnemy(Some("boss"))
        assert(oneBossCell.enemies.isInstanceOf[Enemies])
        oneBossCell.removeEnemy(None)
        assert(oneBossCell.enemies.isInstanceOf[Enemies])
      }
      "if a Player is Fighting with Enemy and 1 Power" in {
        val warCell = emptyCell.addEnemy("zombie").addPlayer(Player("TestPlayer")).attackEnemy(Player("TestPlayer"), 1)
        warCell should be (None)
      }
      "if a Player is Fighting with Enemy and 2 Power" in {
        val warCellZ = emptyCell.addEnemy("zombie").addPlayer(Player("TestPlayer")).attackEnemy(Player("TestPlayer"), 2)
        warCellZ should be (Some("zombie"))
      }
      "if a Player is Fighting with Enemy and 3 Power" in {
        val warCellM = emptyCell.addEnemy("mutant").addPlayer(Player("TestPlayer")).attackEnemy(Player("TestPlayer"), 3)
        warCellM should be (Some("mutant"))
      }
      "if a Player is Fighting with Mutant and 1 Power" in {
        val warCellM = emptyCell.addEnemy("mutant").addPlayer(Player("TestPlayer")).attackEnemy(Player("TestPlayer"), 1)
        warCellM should be (None)
      }
      "if a Player is Fighting with Enemy and 5 Power" in {
        val warCellM = emptyCell.addEnemy("boss").addPlayer(Player("TestPlayer")).attackEnemy(Player("TestPlayer"), 5)
        warCellM should be (Some("boss"))
      }
      "if a Player is Fighting with Boss and 3 Power" in {
        val warCellM = emptyCell.addEnemy("boss").addPlayer(Player("TestPlayer")).attackEnemy(Player("TestPlayer"), 3)
        warCellM should be (None)
      }
      "if there is no enemy to fight" in {
          val nowarCell = emptyCell.addPlayer(Player("TestPlayer")).attackEnemy(Player("TestPlayer"), 5)
        nowarCell should be (None)
      }


      "if Fog Value is Set" in {
        val fogCell = emptyCell.setFogValue(1)
        fogCell.fog should be (Fog(1))
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
      }
    }
  }


}

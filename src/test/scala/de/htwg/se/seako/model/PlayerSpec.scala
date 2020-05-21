package de.htwg.se.seako.model

import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers{
  "A Player" when {
    "not set to any value" should {
      val noNamePlayer = Player("")
      "have no Name" in {
        noNamePlayer.toString should be ("")
      }
    }
    "has the name Bob" should {
      val PlayerBob = Player("Bob")
      "return the Name" in {
        PlayerBob.name should be ("Bob")
      }
      "to String" in {
        PlayerBob.toString should be ("Bob")
      }
    }
  }

}

package de.htwg.se.seako.model

import org.scalatest.{Matchers, WordSpec}

class DiceSpec extends WordSpec with Matchers{
  "A dice" when{
    "not set to any value" should{
      val defaultDice = Dice()
      "have value between" in {
        defaultDice.min should be(1)
        defaultDice.max should be(6)
        1 to 6 contains defaultDice.rolldice
      }
    }
    "if min or max is Set to differt value" should {
      val diceSet = Dice(0,8)
      "set the dice values" in {
        diceSet.min should be(0)
        diceSet.max should be(8)
        0 to 8 contains diceSet.rolldice
      }
    }
  }

}

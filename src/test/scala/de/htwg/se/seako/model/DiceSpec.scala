package de.htwg.se.seako.model

import org.scalatest.{Matchers, WordSpec}

class DiceSpec extends WordSpec with Matchers{
  val dice = Dice()


  "A Dice" should{
    "have a vlaue" in{
      dice.min should be (1)
      dice.max should be (6)
      dice.rolldice shouldBe a [Integer]
      dice.value should be <= 6
      dice.value should be >= 1
    }
  }

}

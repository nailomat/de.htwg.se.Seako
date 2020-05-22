package de.htwg.se.seako.model

import org.scalatest.{Matchers, WordSpec}

class FinishSpec extends WordSpec with Matchers{
  "A Start" when {
    "not set to any Cell " should {
      val noFinish = Finish(0)
      "have value 0" in {
        noFinish.value should be(0)
      }
      "not be set" in {
        noFinish.isSet should be(false)
      }
    }
    "set to a specific Cell" should {
      val finishSet = Finish(1)
      "return that value" in {
        finishSet.value should be(1)
      }
      "be set" in {
        finishSet.isSet should be(true)
      }
    }
  }
}

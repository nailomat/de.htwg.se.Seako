package de.htwg.se.seako.model

import org.scalatest.{Matchers, WordSpec}

class StartSpec extends WordSpec with Matchers{
  "A Start" when {
    "not set to any Cell " should {
      val noStart = Start(0)
      "have value 0" in {
        noStart.value should be(0)
      }
      "not be set" in {
        noStart.isSet should be(false)
      }
    }
    "set to a specific Cell" should {
      val startSet = Start(1)
      "return that value" in {
        startSet.value should be(1)
      }
      "be set" in {
        startSet.isSet should be(true)
      }
    }
  }
}

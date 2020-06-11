package de.htwg.se.seako.model

import org.scalatest.{Matchers, WordSpec}
class FogSpec extends WordSpec with Matchers {
  "A Fog" when{
    "not set to any Cell " should {
      val noFog = Fog(0)
      "have value 0" in {
        noFog.value should be(0)
      }
      "not be set" in {
        noFog.isSet should be(false)
      }
    }
    "set to a specific Cell" should {
      val fogSet = Fog(1)
      "return that value" in {
        fogSet.value should be(1)
      }
      "be set" in {
        fogSet.isSet should be(true)
      }
    }
  }


}

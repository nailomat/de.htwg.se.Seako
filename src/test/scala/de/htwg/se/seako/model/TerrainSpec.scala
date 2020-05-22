package de.htwg.se.seako.model

import org.scalatest.{Matchers, WordSpec}

class TerrainSpec extends WordSpec with Matchers{
  "A Terrain" when {
    "not set to any Cell " should {
      val noTerrain = Terrain(0)
      "have value 0" in {
        noTerrain.value should be(0)
      }
      "not be set" in {
        noTerrain.isSet should be(false)
      }
    }
    "set to a specific Cell" should {
      val terraiSet = Terrain(1)
      "return that value" in {
        terraiSet.value should be(1)
      }
      "be set" in {
        terraiSet.isSet should be(true)
      }
    }
  }
}

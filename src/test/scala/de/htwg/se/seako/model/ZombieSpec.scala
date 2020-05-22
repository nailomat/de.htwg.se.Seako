package de.htwg.se.seako.model

import org.scalatest.{Matchers, WordSpec}

class ZombieSpec extends WordSpec with Matchers{
  "A Zombie" when {
    "is not alive anymore" should {
      val deadZombie = Zombie(0)
      "have value 0" in {
        deadZombie.value should be(0)
      }
      "not be alive" in {
        deadZombie.isAlive should be(false)
      }
    }
  }
}

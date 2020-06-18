package de.htwg.se.seako.model

import org.scalatest.{Matchers, WordSpec}

class EnemiesSpec extends WordSpec with Matchers{
  "A Enemy" when {
    "not set" should {
      val noEnemy = Enemies(Nil, Nil, Nil)
      "have the Nil Zombie, Mutants or Bosses" in {
        noEnemy.zombies should be (Nil)
        noEnemy.mutants should be (Nil)
        noEnemy.bosses should be (Nil)
      }
      "if one Zombie, two mutants and one boss is addes" in {
        val enemiesAreThere = noEnemy.addZombie(Zombie())
        enemiesAreThere.zombies should be (List(Zombie()))
      }
    }
  }


}

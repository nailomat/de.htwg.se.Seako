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
      "if one Zombie is addes" in {
        val enemiesAreThere = noEnemy.addZombie(Zombie())
        enemiesAreThere.zombies should be(List(Zombie()))
      }
      "if two Mutants are added" in {
        val enemiesAreThere = noEnemy.addMutant(Mutant()).addMutant(Mutant())
        enemiesAreThere.mutants should be(List(Mutant(), Mutant()))
      }
      "if one boss is added" in {
        val enemiesAreThere = noEnemy.addBoss(Boss())
        enemiesAreThere.addBoss(Boss())
        enemiesAreThere.bosses should be (List(Boss()))
      }
      "if the Player is weak then there is a attacking zombie" in {
        val enemyAttack = noEnemy.addZombie(Zombie())
        enemyAttack.getValue should be(1)
      }
      "if the Player is weak then there is a attacking zombie and mutant" in {
        val enemyAttack = noEnemy.addMutant(Mutant()).addZombie(Zombie())
        enemyAttack.getValue should be (3)
      }
      "if there is a boss attacking" in {
        val bossAttak = noEnemy.addBoss(Boss())
        bossAttak.getValue should be (5)
      }
    }
  }


}

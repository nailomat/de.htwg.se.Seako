package de.htwg.se.seako.model

import de.htwg.se.seako.model
import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable

class EnemiesSpec extends WordSpec with Matchers{
  "A Enemy" when {
    "not set" should {
      val noEnemy = Enemies(List())
      "have the Nil Zombie, Mutants or Bosses" in {
        noEnemy.enemies should be (List())
      }
      "if one Zombie is addes" in {
        val enemiesAreThere = noEnemy.addEnemy("zombie")
        enemiesAreThere.enemies.contains(List(Enemy.apply("zombie"))) should be (false)
      }
      "if two Mutants are added" in {
        val enemiesAreThere = noEnemy.addEnemy("mutant")
        enemiesAreThere.enemies.contains(List(Enemy.apply("zombie"))) should be (false)
      }
      "if one boss is added" in {
        val enemiesAreThere = noEnemy.addEnemy("boss")
        enemiesAreThere.enemies.contains(List(Enemy.apply("zombie"))) should be (false)
      }

      }
    }


}

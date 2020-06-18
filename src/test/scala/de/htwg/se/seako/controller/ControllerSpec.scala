package de.htwg.se.seako.controller

import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers{
  /*
  "A Controller" when {
    "observed by an Observer" should {
      val controller = new Controller(new Grid[Cell](5,Cell(Nil, Nil,Terrain(0),Fog(0))), PlayerList(Nil))
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated

        override def update(): Unit = updated = true
      }
      controller.add(observer)
      "notify its Observer after creation" in {
        controller.createEmptyGrid(5)
        observer.updated should be (true)
        controller.grid.size should be (5)
      }
      "notify its Observer after adding a zombie" in {
        controller.createEmptyGrid(5)
        controller.addZombie(0,0)
        observer.updated should be (true)
        controller.grid.cell(0,0).zombies.length should be (1)
      }
      "notify its Observer after adding a Player P1" in {
        controller.createEmptyGrid(5)
        controller.addPlayer(0,0, "P1")
        observer.updated should be (true)
        controller.grid.cell(0,0).players.length should be (1)
      }
      "notify its Observer after removing a Player P1" in {
        controller.createEmptyGrid(5)
        controller.addPlayer(0,0,"P1")
        controller.addPlayer(0,0,"P2")
        controller.removePlayer(0,0,"P2")
        observer.updated should be (true)
        controller.grid.cell(0,0).players.length should be (1)
      }
      "print out the empty grid with a size 0" in {
        controller.createEmptyGrid(0)
        controller.gridToString should be ("\n")
      }
      "won't notify controller" in {
        controller.remove(observer)
        observer.updated should be (true)
      }
    }
  }
*/
}

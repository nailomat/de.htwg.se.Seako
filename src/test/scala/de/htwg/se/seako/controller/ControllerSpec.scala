package de.htwg.se.seako.controller

import de.htwg.se.seako.model.{Cell, Fog, Grid, Terrain}
import de.htwg.se.seako.util.Observer
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers{
  "A Controller" when {
    "observed by an Observer" should {
      val controller = new Controller(new Grid[Cell](5,Cell(Nil, Nil,Terrain(0),Fog(0))))
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
    }
  }

}

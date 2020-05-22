package de.htwg.se.seako.model

import org.scalatest.{Matchers, WordSpec}

class GridSpec extends WordSpec with Matchers {
  val cell = Cell(1)
  "A Grid is the playingfield of Seako. A Grid" when {
    "empty" should {
      "be created by using a dimention and a sample cell" in {
        val tinyGrid = new Grid[Cell](2, Cell(0));
        tinyGrid.size should be (2)
      }
      "for test purposes only be created with a Vector of Vectors" in {
      }
    }
  }

}

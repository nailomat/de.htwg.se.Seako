package de.htwg.se.seako.model

import org.scalatest.{Matchers, WordSpec}

class GridSpec extends WordSpec with Matchers {
  /*
  "A Grid" when{
    "empty" should{
      val firstGrid = new Grid[Cell](3,Cell(Nil, Nil, Terrain(0), Fog(0)))
      "be created for the first time " in {
        firstGrid.size should be (3)
        firstGrid.cell(0,0) should be (Cell(Nil, Nil, Terrain(0), Fog(0)))
      }
      "be filled" in {
        val filledGrid = firstGrid.fill(Cell(Nil, Nil, Terrain(0), Fog(1)))
        filledGrid.cell(0,0) should be (Cell(Nil, Nil, Terrain(0), Fog(1)))
      }
      "a Cell be replaced" in{
        val replacedInGrid = firstGrid.replaceCell(0,1,Cell(List(Player("Player1")), Nil, Terrain(0), Fog(0)))
        replacedInGrid.cell(0,1) should be (Cell(List(Player("Player1")), Nil, Terrain(0), Fog(0)))
        replacedInGrid.toString should be
        "■ Player1 ■" +
          "■  ■ ■" +
          "■  ■ ■"
      }
      "if firstGrid has fog on it" in{
        val fogGrid = new Grid[Cell](2,Cell(Nil, Nil, Terrain(0),Fog(1)))
        fogGrid.toString should be (
          "\n" + fogGrid.cell(0,0).fogTopRow() + "\t" +
          fogGrid.cell(0,1).fogTopRow() + "\t\n" +

          fogGrid.cell(0,0).fogPlayerRow() + "\t" +
          fogGrid.cell(0,1).fogPlayerRow() + "\t\n" +

          fogGrid.cell(0,0).fogZombieRow() +"\t" +
          fogGrid.cell(0,1).fogZombieRow() +"\t\n" +

          fogGrid.cell(0,0).fogBottomRow() +"\t" +
          fogGrid.cell(0,1).fogBottomRow() +"\t\n" +

          fogGrid.cell(1,0).fogTopRow() + "\t" +
          fogGrid.cell(1,1).fogTopRow() + "\t\n" +

          fogGrid.cell(1,0).fogPlayerRow() + "\t" +
          fogGrid.cell(1,1).fogPlayerRow() + "\t\n" +

          fogGrid.cell(1,0).fogZombieRow() +"\t" +
          fogGrid.cell(1,1).fogZombieRow() +"\t\n" +

          fogGrid.cell(1,0).fogBottomRow() +"\t" +
          fogGrid.cell(1,1).fogBottomRow() +"\t\n"
        )
      }
    }
  }

   */
}

package de.htwg.se.seako.model

import org.scalatest.{Matchers, WordSpec}

class GridSpec extends WordSpec with Matchers {
  "A Grid" when{
    "empty" should{
      val firstGrid = new Grid[Cell](3,Cell(Nil, Enemies(Nil) , Terrain(0), Fog(0)))
      "be created for the first time " in {
        firstGrid.size should be (3)
        firstGrid.cell(0,0) should be (Cell(Nil, Enemies(Nil), Terrain(0), Fog(0)))
      }
      "be filled" in {
        val filledGrid = firstGrid.fill(Cell(Nil, Enemies(Nil), Terrain(0), Fog(1)))
        filledGrid.cell(0,0) should be (Cell(Nil, Enemies(Nil), Terrain(0), Fog(1)))
      }
      "a Cell be replaced" in{
        val replacedInGrid = firstGrid.replaceCell(0,1,Cell(List(Player("Player1")), Enemies(Nil), Terrain(0), Fog(0)))
        replacedInGrid.cell(0,1) should be (Cell(List(Player("Player1")), Enemies(Nil), Terrain(0), Fog(0)))
        replacedInGrid.toString should be
        "■ Player1 ■" +
          "■  ■ ■" +
          "■  ■ ■"
      }
      "player Position is wanted" in{
        val playerposGrid = firstGrid.playerPos(Player("TestPlayer"))
        playerposGrid should be (0,0,firstGrid.cell(0,0))
      }

      "a player is moved down" in {
        val movplayerDown = firstGrid.movePlayer(Player("TestPlayer"), "down")
        movplayerDown should be (1, 0)
      }

      "a player is moved right" in {
        val movplayerRight = firstGrid.movePlayer(Player("TestPlayer"), "right")
        movplayerRight should be (0, 1)

      }
      "a player is moved up" in {
        val movplayerUp = firstGrid.movePlayer(Player("TestPlayer"), "up")
        movplayerUp should be (0, 0)
      }
      "a player is moved left" in {
      val movplayerleft = firstGrid.movePlayer(Player("TestPlayer"), "left")
        movplayerleft should be (0, 0)
      }

      "if firstGrid has fog on it" in{
        val fogGrid = new Grid[Cell](2,Cell(Nil, Enemies(Nil), Terrain(0),Fog(1)))
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
}

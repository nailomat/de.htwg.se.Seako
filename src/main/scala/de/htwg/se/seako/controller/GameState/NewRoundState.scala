package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.model.Cell
import de.htwg.se.seako.Seako.controller._
import scala.util.Random

case class NewRoundState() extends State {

  override def handle(e: State): State = newRound


  def newRound:State = {
   println("new Round")
    val tickets = 100
    val lottery = Random.nextInt(tickets)
    if (lottery < 60) {
      for (5 <- 0 until 5) {
        val a = Random.nextInt(grid.size)
        val b = Random.nextInt(grid.size)
        setCell(a, b, grid.cell(a, b).addEnemy("zombie"))
      }
    }
    else if (lottery >= 60 && lottery < 90) {
      for (3 <- 0 until 3) {
        val a = Random.nextInt(grid.size)
        val b = Random.nextInt(grid.size)
        setCell(a, b, grid.cell(a, b).addEnemy("mutant"))
      }
    }
    else if (lottery >= 90) {
      for (1 <- 0 until 1) {
        val a = Random.nextInt(grid.size)
        val b = Random.nextInt(grid.size)
        setCell(a, b, grid.cell(a, b).addEnemy("boss"))
      }
    }
    PlayerTurnState()
  }
}

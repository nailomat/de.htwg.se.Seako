package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.controller.Controller
import scala.util.Random

case class NewRoundRunner(controller: Controller) extends StateRunner {
  def on()={
    println("new Round")
    val tickets = 100
    val lottery = Random.nextInt(tickets)
    if(lottery < 60) {
      for (5 <- 0 until 5) {
        val a = Random.nextInt(controller.grid.size)
        val b = Random.nextInt(controller.grid.size)
        controller.setCell(a, b, controller.grid.cell(a, b).addEnemy("zombie"))
      }
    }
    else if(lottery >= 60 && lottery < 90) {
      for(3 <- 0 until 3){
       val a = Random.nextInt(controller.grid.size)
       val b = Random.nextInt(controller.grid.size)
       controller.setCell(a, b,controller.grid.cell(a, b).addEnemy("mutant"))
      }
    }
    else if(lottery >= 90){
      for(1 <- 0 until 1){
        val a = Random.nextInt(controller.grid.size)
        val b = Random.nextInt(controller.grid.size)
        controller.setCell(a, b,controller.grid.cell(a, b).addEnemy("boss"))
      }
    }

  }

  def off()={
    println("new Enemys are on their way")

  }

}

package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.controller._


case class CreateGameRunner(controller: Controller, input: String) extends StateRunner {
  def on() {
    val splitInput = input.split(" ")
    val numberPlayers = controller.playerList.players.size
    splitInput.length match {
      case 1 =>
        splitInput(0) match {
          case "small" =>
            controller.createEmptyGrid(5)
            for(controller.playerList.players <- 0 until numberPlayers) {
              controller.setCell(0, 0, controller.grid.cell(0, 0)
                .addPlayer(controller.playerList.getCurrentPlayer))
            }
          case "medium" =>
            controller.createEmptyGrid(10)
            for(controller.playerList.players <- 0 until numberPlayers) {
              controller.setCell(0, 0, controller.grid.cell(0, 0)
                .addPlayer(controller.playerList.getCurrentPlayer))
            }
          case "big" =>
            controller.createEmptyGrid(20)
            for(controller.playerList.players <- 0 until numberPlayers) {
              controller.setCell(0, 0, controller.grid.cell(0, 0)
                .addPlayer(controller.playerList.getCurrentPlayer))
            }
          case _ => println("unknown size")
        }
      case _ =>
    }



  }
  def off(): Unit = {
    println("The Grid is being constructed")
  }
}

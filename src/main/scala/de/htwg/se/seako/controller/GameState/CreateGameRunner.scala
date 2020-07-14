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
            for(numberPlayers <- controller.playerList.players) {
              controller.setCell(0, 0, controller.grid.cell(0, 0)
                .addPlayer(controller.playerList.getCurrentPlayer))
            }
          case "medium" =>
            controller.createEmptyGrid(10)
            for(numberPlayers <- controller.playerList.players) {
              controller.setCell(0, 0, controller.grid.cell(0, 0)
                .addPlayer(controller.playerList.getCurrentPlayer))
            }
          case "big" =>
            controller.createEmptyGrid(20)
            for(numberPlayers <- controller.playerList.players) {
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

package de.htwg.se.seako.controller.GameState

import de.htwg.se.seako.controller._

case class InsertPlayerRunner(controller: Controller, input: String) extends StateRunner {
  def on():Unit = {
    println("Insert Players")
    while (true) {
      val splitInput = input.split(" ")
      splitInput.length match {
        case 1 =>
          input match {
            case "y" =>
              println("INSERT NAME:")
            case "n" =>
              println("SELECT FIELD SIZE:")
              println("small | medium | big")
              false
            case _ =>
              controller.addPlayerList(splitInput(0))
              println(controller.playerList)
          }
        case _ =>

      }
    }
  }

  def off(): Unit = {
    println("Have Fun")
  }
}

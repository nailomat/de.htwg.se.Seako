package de.htwg.se.seako.aview

import de.htwg.se.seako.controller.Controller
import de.htwg.se.seako.model.{Cell, Finish, Fog, Grid, Player, Start, Terrain, Zombie}
import de.htwg.se.seako.util.Observer

class Tui(controller: Controller) extends Observer{

  controller.add(this)
  def processInputLine(input: String): Unit= {
    input match {
      case "q" =>
      case "t" => controller.createEmptyGrid(5)
      case "small" => controller.createEmptyGrid(5)
      case "medium" => controller.createEmptyGrid(10)
      case "big" => controller.createEmptyGrid(20)
      case _ => validateLongString(input)
    }

      def validateLongString(input: String): Unit = {
        if (input.nonEmpty) {
          val splitInput = input.split(" ")
          splitInput.length match {
            case 3 =>
              val command = splitInput(0)
              val row = splitInput(1).toInt
              val col = splitInput(2).toInt
              if (command.equals("addZombie")) {
                controller.addZombie(row, col)
              }
            case 4 =>
              val command = splitInput(0)
              val row = splitInput(1).toInt
              val col = splitInput(2).toInt
              val value = splitInput(3)
              if (command.equals("addPlayer")) {
                controller.addPlayer(row, col, value)
              }
              if (command.equals("removePlayer")) {
                controller.removePlayer(row, col, value)
              }
            case _ =>
          }
        }
      }

  }



  override def update(): Unit = println(controller.gridToString)
}
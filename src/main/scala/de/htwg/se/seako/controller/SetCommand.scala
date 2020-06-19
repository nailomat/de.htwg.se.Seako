package de.htwg.se.seako.controller

import de.htwg.se.seako.model._
import de.htwg.se.seako.util.Command

class SetCommand (row:Int, col: Int, cell: Cell, controller: Controller) extends Command{

  var tmpCell = controller.grid.cell(row, col)

  override def doStep(): Unit = {
    controller.grid = controller.grid.replaceCell (row, col, cell)
  }

  override def undoStep(): Unit = {
    controller.grid = controller.grid.replaceCell(row,col,tmpCell)
  }

  override def redoStep(): Unit = controller.grid = controller.grid.replaceCell(row, col, cell)

}



package de.htwg.se.seako.controller

import de.htwg.se.seako.model._
import de.htwg.se.seako.util.Command

class SetCommand (row:Int, col: Int, cell: Cell, controller: Controller) extends Command{

  var tmpCell = Cell(Nil,Enemies(),Terrain(0),Fog(1))

  override def doStep(): Unit = {
    tmpCell = controller.grid.cell(row, col)
    controller.grid = controller.grid.replaceCell (row, col, tmpCell)
  }

  override def undoStep(): Unit = {
    controller.grid = controller.grid.replaceCell(row,col,)
  }

  override def redoStep(): Unit = controller.grid = controller.grid.replaceCell(row, col, cell)

}



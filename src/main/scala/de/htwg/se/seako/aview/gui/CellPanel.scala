package de.htwg.se.seako.aview.gui

import de.htwg.se.seako.controller.{CellChange, Controller}
import de.htwg.se.seako.model.Cell

import scala.swing._

class CellPanel(row: Int, col: Int, controller: Controller) extends FlowPanel {

  val givenCellColor = new Color(200, 200, 255)
  val cellColor = new Color(224, 224, 255)
  val highlightedCellColor = new Color(192, 255, 192)

  def myCell = controller.cell(row, col)

  def cellText(row: Int, col: Int) = controller.cell(row, col).asInstanceOf[Cell].fog.value.toString

  val label =
    new Label {
      text = cellText(row, col)
      font = new Font("Verdana", 1, 36)
    }

  val cell = new BoxPanel(Orientation.Vertical) {
    contents += label
    preferredSize = new Dimension(51, 51)
    //    background = if (controller.isGiven(row, col)) givenCellColor else cellColor
    border = Swing.BeveledBorder(Swing.Raised)
    listenTo(mouse.clicks)
    listenTo(controller)
    reactions += {
      case e: CellChange => {
        label.text = cellText(row, col)
        repaint
      }
    }
  }
  //
  //  val candidatelist = (1 to controller.gridSize).map {
  //    (value =>
  //      new Label {
  //        text = if (controller.available(row, column).contains(value)) value.toString else " "
  //        preferredSize = new Dimension(17, 17)
  //        font = new Font("Verdana", 1, 9)
  //        background = cellColor
  //        border = Swing.BeveledBorder(Swing.Raised)
  //        listenTo(mouse.clicks)
  //        listenTo(controller)
  //        reactions += {
  //          case e: CellChange => {
  //            text = if (controller.available(row, column).contains(value)) value.toString else " "
  //            repaint
  //          }
  //          case MouseClicked(src, pt, mod, clicks, pops) => {
  //            controller.set(row, column, value)
  //            text = if (controller.available(row, column).contains(value)) value.toString else " "
  //            repaint
  //          }
  //        }
  //      })
  //  }
  //  val candidates = new GridPanel(controller.gridSize, controller.gridSize) {
  //    setBackground(this)
  //    contents ++= candidatelist
  //  }
  //  contents += candidates

  def redraw = {
    contents.clear()
    label.text = cellText(row, col)
    //      setBackground(cell)
    contents += cell
    repaint
  }

  //
  //  def setBackground(p: Panel) = p.background = if (controller.isGiven(row, column)) givenCellColor
  //  else if (controller.isHighlighted(row, column)) highlightedCellColor
  //  else cellColor
}

package de.htwg.se.seako.aview.gui

import de.htwg.se.seako.controller.{CellChanged, Controller}
import de.htwg.se.seako.model.Cell
import javax.swing.ImageIcon

import scala.swing._

class CellPanel(row: Int, col: Int, controller: Controller) extends FlowPanel {

  val givenCellColor = new Color(200, 200, 255)
  val cellColor = new Color(224, 224, 255)
  val highlightedCellColor = new Color(192, 255, 192)

  val fogLabel: Label = new Label()

  def myCell: Cell = controller.cell(row, col)

  def cellText(row: Int, col: Int): String = controller.cell(row, col).fog.value.toString

  val label : Label =
    new Label {
//      text = cellText(row, col)
//      foreground = java.awt.Color.WHITE
//      font = new Font("Verdana", 1, 10)
      icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/fogCell.PNG")
    }

  val cell: BoxPanel = new BoxPanel(Orientation.Vertical) {
    contents += label
    preferredSize = new Dimension(80, 80)
    background = java.awt.Color.BLACK
    //    background = if (controller.isGiven(rCellow, col)) givenCellColor else cellColor
    border = Swing.BeveledBorder(Swing.Raised)
    listenTo(mouse.clicks)
    listenTo(controller)
    reactions += {
      case e: CellChanged => {
        label.text = cellText(row, col)
        repaint
      }
    }
  }

  contents += cell

  def redraw(): Unit = {
    contents.clear()
    label.text = cellText(row, col)
    contents += cell
    repaint
  }

}

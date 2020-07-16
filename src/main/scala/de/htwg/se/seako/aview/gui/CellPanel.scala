package de.htwg.se.seako.aview.gui

import de.htwg.se.seako.controller.Controller
import de.htwg.se.seako.model.Cell
import javax.swing.ImageIcon

import scala.swing._

class CellPanel(row: Int, col: Int, controller: Controller) extends FlowPanel {

  def myCell: Cell = controller.cell(row, col)

  def myCellType: String = {
    var cellType = ""
    if (myCell.fog.value == 1) {
      cellType = "fog"
    } else {
      if (myCell.terrain.value == 1) {
        cellType = "terrain"
      } else {
        if (myCell.players.nonEmpty) {
          cellType = "player"
        }
      }
    }
    cellType
  }

  def setCell(): Label = {
    val label: Label = new Label
    myCellType match {
      case "fog" => label.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/FogCell.png")
      case "field" => label.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/fieldCell.PNG")
      case "terrain" => label.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/avíew/media/MountainCell.png")
      case "player" => label.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/SelectPlayerCell.PNG")
    }
    preferredSize = new Dimension(80, 80)
    visible = true
    listenTo(mouse.clicks)
    listenTo(controller)
    label
  }

  val playerCell: BoxPanel = new BoxPanel(Orientation.Vertical) {
    val label: Label = new Label
    label.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/SelectPlayerCell.PNG")
    preferredSize = new Dimension(80, 80)
    visible = true
    listenTo(controller)
    contents += label
  }


  def redraw(): Unit = {

    contents.clear()
    val cell = setCell()
    contents += playerCell
    repaint()
  }

}

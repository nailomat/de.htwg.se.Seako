package de.htwg.se.seako.aview.gui

import de.htwg.se.seako.controller.{CellChanged, Controller}
import de.htwg.se.seako.model.Cell
import javax.swing.ImageIcon

import scala.swing._

class CellPanel(row: Int, col: Int, controller: Controller) extends FlowPanel {

  def myCell: Cell = controller.cell(row, col)

  def myCellType: String = {
    var cellType = ""
    if (myCell.players.nonEmpty) {
      cellType = "player"
    } else if (myCell.fog.value == 1) {
      cellType = "fog"
    } else {
      if (myCell.terrain.value == 1) {
        cellType = "terrain"
      }
    }
    cellType
  }

  val cell: BoxPanel = new BoxPanel(Orientation.Vertical) {
    val label: Label = new Label
    label.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/FogCell.png")
    preferredSize = new Dimension(80, 80)
    visible = true
    listenTo(controller)
    reactions += {
      case e: CellChanged => {
        myCellType match {
          case "fog" => label.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/FogCell.png")
          case "field" => label.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/fieldCell.PNG")
          case "terrain" => label.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/avíew/media/MountainCell.png")
          case "player" => label.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/SelectPlayerCell.PNG")
          case _ => label.icon = new ImageIcon("./src/main/scala/de/htwg/se/Seako/aview/media/MountainCell.png")
        }
      }
    }
    contents += label
  }

  contents += cell

  def redraw(): Unit = {
    contents.clear()
    //val cell = cell
    contents += cell
    repaint()
  }

}

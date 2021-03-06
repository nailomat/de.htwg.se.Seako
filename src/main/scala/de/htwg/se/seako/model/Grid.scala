package de.htwg.se.seako.model

case class Grid[T](rows: Vector[Vector[T]]) {
  def this(size: Int, filling: T) = this(Vector.tabulate(size, size) { (row, col) => filling })

  val size: Int = rows.size

  def cell(row: Int, col: Int): Cell = rows(row)(col).asInstanceOf[Cell]

  def fill(filling: T): Grid[T] = copy(Vector.tabulate(size, size) { (row, col) => filling })

  def replaceCell(row: Int, col: Int, cell: T): Grid[T] = copy(rows.updated(row, rows(row).updated(col, cell)))

  def playerPos(player: Player): (Int,Int,Cell) = {
    var currentRow = 0
    var currentCol = 0
    for (row <- 0 until size) {
      for (col <- 0 until size) {
        if (cell(row, col).players.contains(player)) {
          currentCol = col
          currentRow = row
        }
      }
    }
    (currentRow,currentCol,cell(currentRow, currentCol))
  }


  def movePlayer(player: Player, direction: String): (Int,Int) = {
    val position = playerPos(player)
    var newRow = position._1
    var newCol = position._2
    direction match {
      case "up" =>
        if (position._1 == 0){
          newRow = position._1
          newCol = position._2
        } else {
          newRow = position._1 - 1
          newCol = position._2
        }
        (newRow,newCol)
      case "down" =>
        if (position._1 == size){
          newRow = position._1
          newCol = position._2
        } else {
          newRow = position._1 + 1
          newCol = position._2
        }
        (newRow,newCol)
      case "right" =>
        if (position._1 == size){
          newRow = position._1
          newCol = position._2
        } else {
          newRow = position._1
          newCol = position._2 + 1
        }
        (newRow,newCol)
      case "left" =>
        if( position._2 == 0){
          newRow = position._1
          newCol = position._2
        } else {
          newRow = position._1
          newCol = position._2 - 1
        }
        (newRow,newCol)
    }
  }

  override def toString: String = {

    var output = "\n"

    for (row <- 0 until size) {
      for (col <- 0 until size) {
        if (cell(row, col).fog.value > 0 && cell(row, col).players.isEmpty) {
          output += cell(row, col).fogTopRow() + "\t"
        } else {
          output += cell(row, col).topRow() + "\t"
        }
      }
      output += "\n"
      for (col <- 0 until size) {
        if (cell(row, col).fog.value > 0 && cell(row, col).players.isEmpty) {
          output += cell(row, col).fogPlayerRow() + "\t"
        } else {
          output += Console.GREEN + cell(row, col).playerRow() + Console.RESET + "\t            "
        }
      }
      output += "\n"
      for (col <- 0 until size) {
        if (cell(row, col).fog.value > 0 && cell(row, col).players.isEmpty) {
          output += cell(row, col).fogZombieRow() + "\t"
        } else {
          output += Console.RED + cell(row, col).enemyRow() + Console.RESET + "\t            "
        }
      }
      output += "\n"
      for (col <- 0 until size) {
        if (cell(row, col).fog.value > 0 && cell(row, col).players.isEmpty) {
          output += cell(row, col).fogBottomRow() + "\t"
        } else {
          output += cell(row, col).bottomRow() + "\t"
        }
      }
      output += "\n"
    }
    output
  }
}

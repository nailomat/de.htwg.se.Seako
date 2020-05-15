import scala.math.sqrt
case class Cell(zombie: Int, terrain: Boolean, visible: Boolean) {
  def hasZombie:Boolean = zombie != 0
}

val cell1 = Cell(0, true, false)
cell1.hasZombie

val cell2 = Cell(4, false, false)
cell2.hasZombie


case class House(cells:Vector[Cell])

val house = House(Vector(cell1, cell2))
house.cells(0).zombie
house.cells(0).hasZombie

house.cells(1).zombie
house.cells(1).hasZombie

case class Matrix[T] (rows:Vector[Vector[T]]) {
  def this(size:Int, filling:T) = this(Vector.tabulate(size, size){
    (row, col) => filling
  })
  val size:Int = rows.size
  def cell(row:Int, col:Int):T = rows (row)(col)
  def replaceCell(row:Int, col:Int, cell:T):Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))
  def fill (filling:T):Matrix[T] = copy (Vector.tabulate(size, size) {(row, col) => filling})
}

val matrix = Matrix(Vector(Vector(cell1, cell2), Vector(cell2, cell1)))

matrix.rows(0)(0).zombie
matrix.size
matrix.cell(0,0)
matrix.cell(0,0)
matrix.rows(1)(0).zombie

case class Grid(cells: Matrix[Cell]) {
  def this(size: Int) = this(new Matrix[Cell](size, Cell(0, false, false)))
  val size:Int = cells.size
  def cell(row:Int, col:Int):Cell = cells.cell(row, col)
  def set(row:Int, col:Int, value:Int):Grid = copy(cells.replaceCell(row, col, Cell(value, false, true)))
  def row(row:Int):House = House(cells.rows(row))
  def col(col:Int):House = House(cells.rows.map(row=>row(col)))
  def block(block:Int):House = {
    val blocknum:Int = sqrt(size).toInt
    def blockAt(row: Int, col: Int):Int = (col / blocknum) + (row / blocknum) * blocknum
    House((for {
      row <- 0 until size
      col <- 0 until size; if blockAt(row, col) == block
    } yield cell(row, col)).asInstanceOf[Vector[Cell]])
  }
}

val grid1 = new Grid(4)
grid1.cell(0,0).hasZombie

val grid2 = grid1.set(0, 1, 10)
grid2.cell(0,1).hasZombie




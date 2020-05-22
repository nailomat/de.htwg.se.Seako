import scala.math.sqrt
case class Cell(value: Int) {
  def isSet:Boolean = value != 0
}

val cell1 = Cell(0)
cell1.isSet

val cell2 = Cell(4)
cell2.isSet


case class House(cells:Vector[Cell])

val house = House(Vector(cell1, cell2))
house.cells(0)
house.cells(0)
house.cells(1)
house.cells(1)

case class Grid[T] (rows:Vector[Vector[T]]) {
  def this(size:Int, filling:T) = this(Vector.tabulate(size, size){
    (row, col) => filling
  })
  val size:Int = rows.size
  def cell(row:Int, col:Int):T = rows (row)(col)
  def replaceCell(row:Int, col:Int, cell:T):Grid[T] = copy(rows.updated(row, rows(row).updated(col, cell)))
  def fill (filling:T):Grid[T] = copy (Vector.tabulate(size, size) {(row, col) => filling})
}

val grid = Grid(Vector(Vector(cell1, cell2), Vector(cell2, cell1)))

grid.rows(0)(0)
grid.size
grid.cell(0,0)
grid.cell(0,0)
grid.rows(1)(0).isSet

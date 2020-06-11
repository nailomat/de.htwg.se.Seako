import scala.util.Random

case class Cell(value: Int) {
  def isSet:Boolean = value != 0
}

val cell1 = Cell(0)
cell1.isSet

val cell2 = Cell(4)
cell2.isSet

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


case class Dice(min: Integer = 1, max: Integer = 6) {
  def rolldice: Integer = {
    val value = min + Random.nextInt(max)
    value
  }
}
val dice = new Dice(1,6)
dice.rolldice
val dice2 = new Dice(1,6)
dice2.rolldice
val dice3 = new Dice(1,6)
dice3.rolldice
val dice4 = new Dice(1,6)
dice4.rolldice
val dice5 = new Dice(1,6)
dice5.rolldice
val dice6 = new Dice(1,6)
dice6.rolldice

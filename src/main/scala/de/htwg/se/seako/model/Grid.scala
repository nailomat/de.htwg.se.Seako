package de.htwg.se.seako.model

case class Grid[T](rows:Vector[Vector[T]]) {
  def this(size:Int, filling:T) = this(Vector.tabulate(size,size){(row,col) => filling})
  val size:Int = rows.size
  def cell(row:Int, col:Int):T = rows (row)(col)
  def fill(filling:T):Grid[T] = copy( Vector.tabulate(size, size){ (row, col) => filling})
  def replaceCell(row:Int, col:Int, cell:T):Grid[T] = copy(rows.updated(row, rows(row).updated(col,cell)))

  override def toString: String = {

    var output = "\n"

    for (row <- 0 until size) {
      for (col <- 0 until size) {
        output = output + cell(row,col) + "\t"
      }
      output = output + "\n"
    }
    output
  }
}

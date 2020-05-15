case class Field(zombie: Int, terrain: Boolean, weapon: Boolean) {
  def hasZombie:Boolean = zombie != 0
}

val field1 = Field(0, true, false)
field1.hasZombie

val field2 = Field(4, false, false)
field2.hasZombie

case class Matrix[T] (rows:Vector[Vector[T]]) {
  def this(size:Int, filling:T) = this(Vector.tabulate(size, size){
    (row, col) => filling
  })
  val size:Int = rows.size
  def field(row:Int, col:Int):T = rows (row)(col)
  def replaceField(row:Int, col:Int, field:T):Matrix[T] = copy(rows.updated(row, rows(row).updated(col, field)))
  def fill (filling:T):Matrix[T] = copy (Vector.tabulate(size, size) {(row, col) => filling})
}

case class Grid(fields: Matrix[Field])
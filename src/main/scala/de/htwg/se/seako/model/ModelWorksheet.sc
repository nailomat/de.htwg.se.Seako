import de.htwg.se.seako.model.{Finish, Player, Start, Terrain, Zombie}

case class Cell(players : List[Player], zombies: List[Zombie], terrain: Terrain) {

  def addPlayer(player: Player) : Cell = {
    val newPlayers = players :+ player
    this.copy(players = newPlayers)
  }

  override def toString: String = {
    var output = ""
    output += players.mkString(", ") + "\n"
    output += "\n"
    output += zombies.mkString(", ") + "\n"
    output;
  }
}

val cell1 = Cell(List[Player](), List[Zombie](), Terrain(1))
cell1.addPlayer(Player("P1"))
cell1.addPlayer(Player("P2"))
cell1.copy(players = List(Player("P2")))
cell1


case class Matrix[T](rows: Vector[Vector[T]]) {
  def this(size: Int, filling: T) = this(Vector.tabulate(size, size) { (row, col) => filling })

  val size: Int = rows.size

  def cell(row: Int, col: Int): T = rows(row)(col)

  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(size, size) { (row, col) => filling })

  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))
}

val matrix= Matrix[Cell](Vector(Vector(cell1)))
matrix.cell(1,0)
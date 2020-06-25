package de.htwg.se.seako.model

case class Cell(players: List[Player], zombies: List[Zombie] = Nil,mutants: List[Mutant]= Nil,bosses: List[Boss]= Nil, terrain: Terrain, fog: Fog) {

  //  def isSet: Boolean = terrain.value != 0

  def addPlayer(player: Player): Cell = {
    val newPlayers = players :+ player
    this.copy(players = newPlayers)
  }


  def getValue: Integer = {
    var value = 0
    value = value + (zombies.length * 1)
    value = value + (mutants.length * 2)
    value = value + (bosses.length * 5)
    value
  }

  def addZombie(zombie: Zombie): Cell = {
    val newZombies = zombies :+ zombie
    this.copy(zombies = newZombies)
  }

  def addMutant(mutant: Mutant): Cell = {
    val newMutants = mutants :+ mutant
    this.copy(mutants = newMutants)
  }

  def addBoss(boss: Boss): Cell = {
    val newBoss = bosses :+ boss
    this.copy(bosses = newBoss)
  }



  def removePlayer(player: Player): Cell = {
    val newPlayers = players.filterNot(players => players == player)
    this.copy(players = newPlayers)
  }

  def topRow(): String = "⌈          ⌉"

  def fogTopRow(): String = "⌈■■■■■■■■■■⌉"

  def playerRow(): String = players.mkString(", ")

  def fogPlayerRow(): String = "|■■■■■■■■■■|"

  def enemyRow(): String = enemies.zombies.mkString(", ") + enemies.mutants.mkString(", ") + enemies.bosses.mkString(", ")

  def fogZombieRow(): String = "|■■■■■■■■■■|"

  def fogBottomRow(): String = "⌊■■■■■■■■■■⌋"

  def bottomRow(): String = "⌊          ⌋"

}

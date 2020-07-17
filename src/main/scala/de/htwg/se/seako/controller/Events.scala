package de.htwg.se.seako.controller

import scala.swing.event.Event

class StartGame extends Event
class GridSizeChanged(newSize: Int) extends Event
class CellChanged extends Event
class AddPlayer extends Event
class RemovePlayer extends Event
class AddEnemy extends Event
class RemoveZombie extends Event
class PlayerChanged extends Event
class EnemyChanged extends Event
class ChangeEnemy extends Event
package de.htwg.se.seako.controller.GameState

import scala.swing.event.Event

trait State {
  def validateString(e: String): State
}

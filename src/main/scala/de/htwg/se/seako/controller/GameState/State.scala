package de.htwg.se.seako.controller.GameState

trait State {
  def handle(e: State)
}

package de.htwg.se.seako.controller.GameState

case class StartRunner() extends StateRunner {

  def on():Unit = {
     println("\"TYPE \\\"start\\\" TO START THE GAME\"")

  }

  def off(): Unit = {
    println("Have Fun")
  }
}

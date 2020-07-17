package de.htwg.se.seako.aview.gui

import de.htwg.se.seako.Seako
import org.scalatest.{Matchers, WordSpec}

class SwingGuiSpec extends WordSpec with Matchers {

  "A Swing Gui" when {
    "the Game has started" should {
      val seako = Seako
      seako.tui.processInputLine("start")
      seako.tui.processInputLine("Andi")
      seako.tui.processInputLine("y")
      seako.tui.processInputLine("Nail")
      seako.tui.processInputLine("n")
      seako.tui.processInputLine("small")
      "new Game" in {
        seako.gui.newGame() should be ()
      }
      "start Game" in {
        seako.gui.startGame() should be ()
      }
      "show Sizes" in {
        seako.gui.showSizes() should be ()
      }
    }
  }

}

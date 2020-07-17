package de.htwg.se.seako.model
import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable

class PlayerListeSpec extends WordSpec with Matchers {
  "A PlayerList" when{
    "empty" should{
      val playerListempty = PlayerList(List())
      "The List should be empty" in {
      playerListempty should be (PlayerList(List()))
      }
      "Add a Player" in{
        val listAddPlayer = playerListempty.addPlayer(Player("Test"))
        listAddPlayer should be (PlayerList(List(Player("Test"))))
        listAddPlayer.addPlayer(Player("Test"))
        listAddPlayer should be (PlayerList(List(Player("Test"))))
        listAddPlayer.removePlayer(Player("Test"))
        listAddPlayer should be (PlayerList(List(Player("Test"))))
      }
    }
  }
}

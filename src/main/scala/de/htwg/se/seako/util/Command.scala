package de.htwg.se.seako.util

trait Command {

  def doStep():Unit

  def undoStep():Unit

  def redoStep():Unit

}

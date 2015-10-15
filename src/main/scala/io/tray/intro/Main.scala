package io.tray.intro

import java.io.File

import scala.io.Source

object Main extends App {
  val input = Source.fromFile(new File("input.txt")).mkString

  val room = Room.create(input).get
  val hoover = Hoover(room)
  val (position, cleaned) = hoover.hoover()

  println(s"${position.x} ${position.y}")
  println(cleaned)
}

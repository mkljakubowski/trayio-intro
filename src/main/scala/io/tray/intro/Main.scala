package io.tray.intro

import java.io.File

import scala.io.Source

object Main extends App {
  val input = Source.fromFile(new File("input.txt")).mkString

  Room.create(input).flatMap { room =>
    val hoover = Hoover(room)
    hoover.hoover().map { case HooverResult(position, cleaned) =>
      println(s"${position.x} ${position.y}")
      println(cleaned)
    }
  }.getOrElse {
    println("invalid input")
  }
}

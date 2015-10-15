package io.tray.intro

import scala.util.Try

case class Position(x: Int, y: Int)

sealed trait Instruction

case object North extends Instruction
case object South extends Instruction
case object West extends Instruction
case object East extends Instruction

object Instruction {
  def apply(l: Char): Instruction = l match {
    case 'N' => North
    case 'S' => South
    case 'W' => West
    case 'E' => East
  }

  val allowedChars = "NSEW"
}

case class Room(dirtPatches: Seq[Position], startPosition: Position, width: Int, height: Int, instructions: Seq[Instruction])

object Room {

  private def validateInput(data: String): Boolean = {
    val lines = data.split("\n")
    if(lines.length < 3){
      false
    } else {
      val numberTuples = lines.init.forall{ line =>
        val maybeInts = line.split(" ")
        (maybeInts.length == 2) && maybeInts.forall{maybeInt => Try(maybeInt.toInt).isSuccess}
      }
      val instructions = lines.last.forall(l => Instruction.allowedChars.contains(l))
      instructions && numberTuples
    }
  }

  def create(data: String): Option[Room] = {
    if(validateInput(data)) {
      val lines = data.split("\n")
      val linesAsTuples = lines.init.map { line =>
        val ints = line.split(" ").map(_.toInt)
        ints(0) -> ints(1)
      }
      val (width, height) = linesAsTuples.head
      val patches = linesAsTuples.tail.map(Function.tupled(Position.apply))
      val instructions = lines.last.map(Instruction.apply)
      val room = Room(patches.tail, patches.head, width, height, instructions)
      Some(room)
    } else {
      None
    }
  }

}

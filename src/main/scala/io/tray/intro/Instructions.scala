package io.tray.intro

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
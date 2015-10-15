package io.tray.intro

case class Hoover(room: Room) {

  private def move(position: Position, instruction: Instruction): Position = {
    instruction match {
      case North => position.copy(x = position.x - 1)
      case South => position.copy(x = position.x + 1)
      case West => position.copy(y = position.y - 1)
      case East => position.copy(y = position.y + 1)
    }
  }

  def hoover(): (Position, Int) = {
    val start = Position(0, 0)

    val hooverPositions = room.instructions.foldLeft(Seq(start)){ case (positions, instruction) =>
      move(positions.head, instruction) +: positions
    }

    val hooverPositionsSet = hooverPositions.toSet
    val dirtPatchSet = room.dirtPatches.toSet
    val numberOfPatchesCleaned = hooverPositionsSet.intersect(dirtPatchSet).size
    (hooverPositions.head, numberOfPatchesCleaned)
  }
}

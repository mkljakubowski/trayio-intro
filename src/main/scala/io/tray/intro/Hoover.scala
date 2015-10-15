package io.tray.intro

case class Hoover(room: Room) {

  private def move(position: Position, instruction: Instruction): Position = {
    instruction match {
      case West => position.copy(x = position.x - 1)
      case East => position.copy(x = position.x + 1)
      case North => position.copy(y = position.y + 1)
      case South => position.copy(y = position.y - 1)
    }
  }

  def hoover(): (Position, Int) = {
    val hooverPositions = room.instructions.foldLeft(Seq(room.startPosition)) { case (positions, instruction) =>
      move(positions.head, instruction) +: positions
    }

    val hooverPositionsSet = hooverPositions.toSet
    val dirtPatchSet = room.dirtPatches.toSet
    val numberOfPatchesCleaned = hooverPositionsSet.intersect(dirtPatchSet).size
    (hooverPositions.head, numberOfPatchesCleaned)
  }

}

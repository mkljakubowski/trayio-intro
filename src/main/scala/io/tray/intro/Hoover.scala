package io.tray.intro

case class HooverResult(finalPosition: Position, patchesSwept: Int)

case class Hoover(room: Room) {

  /**
   * moving hoover logic
   */
  private def move(position: Position, instruction: Instruction): Position = {
    instruction match {
      case West => position.copy(x = position.x - 1)
      case East => position.copy(x = position.x + 1)
      case North => position.copy(y = position.y + 1)
      case South => position.copy(y = position.y - 1)
    }
  }

  private def checkIfHooverNotCrashedIntoAWall(hooverPositions: Seq[Position]): Boolean = {
    hooverPositions.forall { case Position(x, y) =>
      x >= 0 && y >= 0 && x < room.width && y < room.height
    }
  }

  def hoover(): Option[HooverResult] = {
    val hooverPositions = room.instructions.foldLeft(Seq(room.startPosition)) { case (positions, instruction) =>
      move(positions.head, instruction) +: positions
    }

    if (checkIfHooverNotCrashedIntoAWall(hooverPositions)) {
      val hooverPositionsSet = hooverPositions.toSet
      val dirtPatchSet = room.dirtPatches.toSet
      val numberOfPatchesCleaned = hooverPositionsSet.intersect(dirtPatchSet).size
      Some(HooverResult(hooverPositions.head, numberOfPatchesCleaned))
    } else {
      None
    }
  }

}

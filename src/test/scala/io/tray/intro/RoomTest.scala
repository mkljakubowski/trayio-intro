package io.tray.intro

import org.scalatest._

class RoomTest extends FlatSpec with Matchers {

  it should "process input from exercise" in {
    //GIVEN
    val input = """5 5
                  |1 2
                  |1 0
                  |2 2
                  |2 3
                  |NNESEESWNWW""".stripMargin

    //WHEN
    val room = Room.create(input)
    val resultRoom = Room(
      Seq(Position(1, 0), Position(2, 2), Position(2, 3)),
      Position(1, 2),
      5, 5,
      Seq(North, North, East, South, East, East, South, West, North, West, West)
    )

    //THEN
    room should equal(Some(resultRoom))
  }

  it should "fail for empty input" in {
    //GIVEN
    val input = """""".stripMargin

    //WHEN
    val room = Room.create(input)

    //THEN
    room should equal(None)
  }

  it should "fail for invalid input 1" in {
    //GIVEN
    val input = """5 a
                  |1 2
                  |1 0
                  |2 2
                  |2 3
                  |NNESEESWNWW""".stripMargin

    //WHEN
    val room = Room.create(input)

    //THEN
    room should equal(None)
  }

  it should "fail for invalid input 2" in {
    //GIVEN
    val input = """5 5
                  |1 2
                  |1 0
                  |2 2
                  |2 3
                  |NNESEESW1WW""".stripMargin

    //WHEN
    val room = Room.create(input)

    //THEN
    room should equal(None)
  }

  it should "fail for invalid input 3" in {
    //GIVEN
    val input = """5 5
                  |1 2
                  |1 0 1
                  |2 2
                  |2 3
                  |NNESEESWNWW""".stripMargin

    //WHEN
    val room = Room.create(input)

    //THEN
    room should equal(None)
  }

}
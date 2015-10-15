package io.tray.intro

import org.scalatest._

class HooverTest extends FlatSpec with Matchers {

  it should "process input from exercise with correct result" in {
    //GIVEN
    val input = """5 5
                  |1 2
                  |1 0
                  |2 2
                  |2 3
                  |NNESEESWNWW""".stripMargin
    val room = Room.create(input).get
    val hoover = Hoover(room)

    //WHEN
    val result = hoover.hoover()
    val expectedResult = HooverResult(Position(1, 3), 1)

    //THEN
    result should equal(Some(expectedResult))
  }

  it should "fail when hoover crashes into a wall" in {
    //GIVEN
    val input = """3 3
                  |1 2
                  |1 0
                  |2 2
                  |2 3
                  |NNESEESWNWW""".stripMargin
    val room = Room.create(input).get
    val hoover = Hoover(room)

    //WHEN
    val result = hoover.hoover()

    //THEN
    result should equal(None)
  }


}
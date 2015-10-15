package io.tray.intro

import org.scalatest._

class HooverTest extends FlatSpec with Matchers {

  it should "process input from exercise" in {
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

    //THEN
    result should equal((Position(1, 3), 1))
  }

}
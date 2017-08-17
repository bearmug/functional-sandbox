package org.bearmug.concurrent.intro

import org.scalatest.FunSuite

class ExerciseSuite extends FunSuite {

  test("functions composition works well for Int -> String -> Int") {
    val g = (input: String) => input.toInt * 2
    val f = (input: Int) => (input + 2).toString
    val answer = g(f(22))
    assert(Exercise.compose(g, f)(22) == answer)
  }

  test("functions composition works well for Double -> String -> Option") {
    val g = (input: String) => Some(input)
    val f = (input: Double) => input.toString
    val answer = g(f(22.0))
    assert(Exercise.compose(g, f)(22) == answer)
  }

  List(
    (Some(1), Some("r"), Some(1, "r")),
    (Some(12.0), None, None),
    (None, Some("asd"), None),
    (None, None, None)
  ).foreach {
    case (a, b, res) => test(s"$a fused with $b is $res") {
      assert(Exercise.fuse(a, b) == res)
    }
  }

  List(
    (List(1, 1, 1, 1, 0), (i: Int) => i > 0, false),
    (List(1, 1, 1, 1, 1), (i: Int) => i > 0, true)
  ).foreach { e =>
    test(s"check API call for ${e._1} fused with ${e._2} is ${e._3}") {
      assert(Exercise.checkApi(e._1)(e._2) == e._3)
    }

    test(s"check manual call for ${e._1} fused with ${e._2} is ${e._3}") {
      assert(Exercise.check(e._1)(e._2) == e._3)
    }
  }
}

package org.bearmug.algo

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LISubSequenceSuite extends FunSuite {

  test("empty subsequence for empty list") {
    assert(new LISubSequence(List()).length == 0)
  }

  test("subsequence length for List(1) is 1") {
    assert(new LISubSequence(List(1)).length == 1)
  }

  test("subsequence length for List(1, 2, 3) is 3") {
    assert(new LISubSequence(List(1, 2, 3)).length == 3)
  }

  test("subsequence length for List(3, 2, 1) is 1") {
    assert(new LISubSequence(List(1, 2, 3)).length == 3)
  }

  test("subsequence length for List(3, 1, 2) is 2") {
    assert(new LISubSequence(List(3, 1, 2)).length == 2)
  }

  test("subsequence length for List(3, 1, 2, 4) is 3") {
    assert(new LISubSequence(List(3, 1, 2, 4)).length == 3)
  }

  test("subsequence length for List(3, 1, 2, 20, 4, 5) is 4") {
    assert(new LISubSequence(List(3, 1, 2, 20, 4, 5)).length == 4)
  }
}

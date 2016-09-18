package org.bearmug.rationals

/*
 * This Scala Testsuite was auto generated by running 'gradle init --type scala-library'
 * by 'pavel' at '9/18/16 11:31 PM' with Gradle 2.12
 *
 * @author pavel, @date 9/18/16 11:31 PM
 */

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RationalSuite extends FunSuite {

  test("toString produces concise output") {
    assert(Rational(1, 2).toString == "1/2")
    assert(RationalJ.rational(1, 2).toString == "1/2")
  }

  test("shortcut apply without denominator works fine") {
    assert(Rational(10).toString == "10/1")
    assert(RationalJ.rational(10).toString == "10/1")
  }

  test("negation works fine and concise") {
    assert((-Rational(10, 7)).toString == "-10/7")
    assert(RationalJ.rational(10, 7).negate().toString == "-10/7")
  }
}

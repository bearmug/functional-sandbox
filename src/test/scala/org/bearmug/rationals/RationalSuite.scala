package org.bearmug.rationals

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
  * Suite tests Java/Scala methods at once for demo purposes.
  */

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
    assert(RationalJ.rational(10, 7).negateUnary().toString == "-10/7")
  }

  test("multiplication works as expected") {
    assert((Rational(3, 2) * Rational(7, 5)).toString == Rational(21, 10).toString)
    assert(RationalJ.rational(3, 2).multiply(RationalJ.rational(7, 5)).toString == RationalJ.rational(21, 10).toString)
  }

  test("division works as expected") {
    assert((Rational(3, 2) / Rational(7, 5)).toString == Rational(15, 14).toString)
    assert(RationalJ.rational(3, 2).divide(RationalJ.rational(7, 5)).toString == RationalJ.rational(15, 14).toString)
  }

  test("tuples approach works") {
    assert((Rational (3, 2) / Rational (7, 5)).toString == Rational((15, 14)).toString)
  }

  test("new rationals simplified") {
    assert(Rational(6, 4).toString == "3/2")
    assert(RationalJ.rational(6, 4).toString == "3/2")
  }

  test("multiplication output simplified") {
    assert((Rational(2, 4) * Rational(4, 10)).toString == "1/5")
    assert(RationalJ.rational(2, 4).multiply(RationalJ.rational(4, 10)).toString == RationalJ.rational(1, 5).toString)
  }

  test("plus works as expected") {
    assert((Rational(3, 2) + Rational(7, 5)).toString == Rational(29, 10).toString)
    assert(RationalJ.rational(3, 2).plus(RationalJ.rational(7, 5)).toString == RationalJ.rational(29, 10).toString)
  }

  test("minus works as expected") {
    assert((Rational(3, 2) - Rational(7, 5)).toString == Rational(1, 10).toString)
    assert(RationalJ.rational(3, 2).minus(RationalJ.rational(7, 5)).toString == RationalJ.rational(1, 10).toString)
  }

  test("equals finally works OK") {
    assert(Rational(3, 2) == Rational(3, 2))
    assert(RationalJ.rational(3, 2).equals(RationalJ.rational(3, 2)))
  }

  test("equals negative case triggers fine") {
    assert(Rational(3, 2) != Rational(2, 3))
    assert(!RationalJ.rational(3, 2).equals(RationalJ.rational(2, 3)))
  }

  test("equals negative corner case also OK") {
    assert(!RationalJ.rational(3, 2).equals(null))
    //noinspection ComparingUnrelatedTypes
    assert(!RationalJ.rational(3, 2).equals(""))
  }

  test("greater relational operator works") {
    assert(Rational(3, 2) > Rational(1, 5))
    assert(RationalJ.rational(3, 2).gt(RationalJ.rational(1, 5)))
  }

  test("greater or equals relational operator works") {
    assert(Rational(3, 2) >= Rational(1, 5))
    assert(Rational(3, 2) >= Rational(3, 2))
    assert(RationalJ.rational(3, 2).gtEq(RationalJ.rational(1, 5)))
    assert(RationalJ.rational(3, 2).gtEq(RationalJ.rational(3, 2)))
  }

  test("less relational operator works") {
    assert(Rational(1, 2) < Rational(4, 5))
    assert(RationalJ.rational(1, 2).less(RationalJ.rational(4, 5)))
  }

  test("less or equals relational operator works") {
    assert(Rational(1, 2) <= Rational(4, 5))
    assert(Rational(1, 2) <= Rational(2, 4))
    assert(RationalJ.rational(1, 2).lessEq(RationalJ.rational(4, 5)))
    assert(RationalJ.rational(1, 2).lessEq(RationalJ.rational(2, 4)))
  }

  test("min operator works") {
    assert((Rational(1, 2) min Rational(1, 5)) == Rational(1, 5))
    assert(RationalJ.rational(1, 2).min(RationalJ.rational(1, 5)).equals(RationalJ.rational(1, 5)))
  }

  test("max operator works") {
    assert((Rational(1, 2) max Rational(1, 5)) == Rational(1, 2))
    assert(RationalJ.rational(1, 2).max(RationalJ.rational(1, 5)).equals(RationalJ.rational(1, 2)))
  }
}

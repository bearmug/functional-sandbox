package org.bearmug.rationals

/**
 * Functional rational numbers implementation. Inspired by 'Programming in Scala' book.
 */
class Rational(n: Int, d: Int) {
  val number = n
  val denominator = d

  override def toString: String = s"$n/$d"
  def unary_- = Rational(-n, d)
  def *(o: Rational) = Rational(n * o.number, d * o.denominator)
}

object Rational {
  def apply(n: Int, d: Int): Rational = new Rational(n, d)
  def apply(n: Int): Rational = new Rational(n, 1)
}

package org.bearmug.rationals

/**
 * Functional rational numbers implementation. Inspired by 'Programming in Scala' book.
 */
class Rational(val n: Int, val d: Int) {

  override def toString: String = s"$n/$d"
  def unary_- = Rational(-n, d)
  def *(o: Rational) = Rational(n * o.n, d * o.d)
  def /(o: Rational) = Rational(n * o.d, d * o.n)
}

object Rational {
  def apply(n: Int, d: Int): Rational = new Rational(n, d)
  def apply(n: Int): Rational = new Rational(n, 1)
}

package org.bearmug.rationals

/**
 * Functional rational numbers implementation. Inspired by 'Programming in Scala' book.
 */
class Rational(val n: Int, val d: Int) {

  override def toString: String = s"$n/$d"
  def unary_- = Rational(-n, d)
  def *(o: Rational) = Rational(n * o.n, d * o.d)
  def /(o: Rational) = Rational(n * o.d, d * o.n)
  def +(o: Rational) = Rational(n * o.d + o.n * d, d * o.d)
  def -(o: Rational) = Rational(n * o.d - o.n * d, d * o.d)
  def ==(o: Rational) = this - o match {case Rational(in, _) => in == 0}
  def >(o: Rational) = this - o match {case Rational(in, _) => in > 0}
  def <(o: Rational) = this - o match {case Rational(in, _) => in < 0}
  def >=(o: Rational) = this > o || this == o
  def <=(o: Rational) = this < o || this == o
  def min(o: Rational) = if (this <= o) this else o
  def max(o: Rational) = if (this >= o) this else o
}

object Rational {
  def apply(n: Int, d: Int): Rational = new Rational(n, d)
  def apply(n: Int): Rational = new Rational(n, 1)
  def unapply(r: Rational): Option[(Int, Int)] = Some(r.n: Int, r.d: Int)
}

package org.bearmug.rationals

import scala.annotation.tailrec

/**
 * Functional rational numbers implementation. Inspired by 'Programming in Scala' book.
 */
class Rational private(val n: Int, val d: Int) {

  require(d != 0)

  override def toString: String = s"$n/$d"
  def unary_-():Rational = Rational(-n, d)
  def *(o: Rational): Rational = Rational(n * o.n, d * o.d)
  def /(o: Rational): Rational = Rational(n * o.d, d * o.n)
  def +(o: Rational): Rational = Rational(n * o.d + o.n * d, d * o.d)
  def -(o: Rational): Rational = Rational(n * o.d - o.n * d, d * o.d)
  def ==(o: Rational): Boolean = this - o match {case Rational(in, _) => in == 0}
  def >(o: Rational): Boolean = this - o match {case Rational(in, _) => in > 0}
  def <(o: Rational): Boolean = this - o match {case Rational(in, _) => in < 0}
  def >=(o: Rational): Boolean = this > o || this == o
  def <=(o: Rational): Boolean = this < o || this == o
  def min(o: Rational): Rational = if (this <= o) this else o
  def max(o: Rational): Rational = if (this >= o) this else o
}

object Rational {
  def apply(n: Int, d: Int): Rational = {
    @tailrec
    def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    gcd(n, d) match { case g => new Rational(n/g, d/g) }
  }
  def apply(n: Int): Rational = Rational(n, 1)
  def apply(t: (Int, Int)): Rational = { t match { case (r, d) => Rational(r, d) } }
  def unapply(r: Rational): Option[(Int, Int)] = Some(r.n, r.d)
}

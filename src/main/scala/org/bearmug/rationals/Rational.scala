package org.bearmug.rationals

import scala.annotation.tailrec

trait RichOrdered[T <: RichOrdered[T]] extends Ordered[T] {
  def min(o: T): Ordered[T] = if (this <= o) this else o
  def max(o: T): Ordered[T] = if (this >= o) this else o
}
/**
  * Functional rational numbers implementation. Inspired by 'Programming in Scala' book. See
  * [[http://booksites.artima.com/programming_in_scala/examples/html/ch06.html book example.]]
  */
class Rational private(val n: Int, val d: Int) extends RichOrdered[Rational] {

  require(d != 0)

  override def toString: String = s"$n/$d"
  override def equals(obj: scala.Any): Boolean = obj match {
    case o: Rational => this - o match { case Rational(on, _) => on == 0 }
    case _ => false
  }
  override def compare(that: Rational): Int = this.n * that.d - that.n * this.d

  def unary_-():Rational = Rational(-n, d)
  def *(o: Rational): Rational = Rational(n * o.n, d * o.d)
  def /(o: Rational): Rational = Rational(n * o.d, d * o.n)
  def +(o: Rational): Rational = Rational(n * o.d + o.n * d, d * o.d)
  def -(o: Rational): Rational = Rational(n * o.d - o.n * d, d * o.d)
}

object RationalConversions {
  implicit def tuple2Rational(t: (Int, Int)): Rational = t match {
    case (n, d) => Rational(n, d)
  }
  implicit def int2Rational(i: Int): Rational = Rational(i)
}

object Rational {
  def apply(n: Int, d: Int): Rational = {
    @tailrec
    def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    gcd(n, d) match { case g => new Rational(n/g, d/g) }
  }
  def apply(n: Int): Rational = Rational(n, 1)
  def apply(t: (Int, Int)): Rational = t match { case (r, d) => Rational(r, d) }
  def unapply(r: Rational): Option[(Int, Int)] = Some(r.n, r.d)
}

/**
 * Functional rational numbers implementation. Inspired by 'Programming in Scala' book.
 */
class Rational(n: Int, d: Int) {
  override def toString: String = s"$n/$d"
}

object Rational {
  def apply(n: Int, d: Int): Rational = new Rational(n, d)

  def apply(n: Int): Rational = new Rational(n, 1)
}

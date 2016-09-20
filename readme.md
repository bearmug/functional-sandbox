[![Build Status](https://travis-ci.org/bearmug/functional-sandbox.svg?branch=master)](https://travis-ci.org/bearmug/functional-sandbox) [![Coverage Status](https://coveralls.io/repos/github/bearmug/functional-sandbox/badge.svg?branch=5-rationals-problem---describe-and-re-use)](https://coveralls.io/github/bearmug/functional-sandbox?branch=5-rationals-problem---describe-and-re-use) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/b0b71d6e74b14b58baffafce3ef1d550)](https://www.codacy.com/app/pavel-fadeev/functional-sandbox?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=bearmug/functional-sandbox&amp;utm_campaign=Badge_Grade)
 
# Playing around rational numbers
With great kickoff from [Programming in Scala](https://www.amazon.com/Programming-Scala-Updated-2-12/dp/0981531687) about [rationals construction](http://booksites.artima.com/programming_in_scala/examples/html/ch06.html)
I`m really interested if it is possible to implement similar stuff with
Java and may be do more with Scala concise.

## Default details - unchanged
Implementation contains regular arithmetic operations ``+, -, /, *``. It
also has unary ``-`` and ``min/max`` functions to enable simple 
selections. Validation also implemented by common ``Predef.require``
call.

## *equals* and *toString* overridden
Formatted ``toString`` looks a bit shorter than direct concatenation and 
served at compile time:
```scala
  override def toString: String = s"$n/$d"
```

And ``equals`` code simple now as 1-2-3. At the same time it keeps 
contract with null reference and wrong class instance. Now ``Rational``
assertions simple and does not require ``toString`` usage.
```scala
  override def equals(obj: scala.Any): Boolean = obj match {
    case o: Rational => this - o match { case Rational(on, _) => on == 0 }
    case _ => false
  }
```

## Relational operations
Arithmetic operations just implemented as defined into original, nothing
interesting there. Here is the link to the [Rational.scala](src/main/scala/org/bearmug/rationals/Rational.scala) source to
review. 

Really exciting part is rationals comparison operations. Those ones clean
and self-explanatory:
```scala
  def >(o: Rational): Boolean = this - o match { case Rational(in, _) => in > 0 }
  def <(o: Rational): Boolean = this - o match { case Rational(in, _) => in < 0 }
  def >=(o: Rational): Boolean = this > o || this == o
  def <=(o: Rational): Boolean = this < o || this == o
```

## Rationals construction
As a very first step, [GCD](https://en.wikipedia.org/wiki/Greatest_common_divisor) moved to companion object. 
Our rational numbers have to be simplified with GCD, but if we`ll do 
this inside ``Rational`` class construction, there is a chance that we 
will use initial (before ``GCD``) values for subsequent calclations. It may 
looks like:
```scala
class SampleThing(p: Int) {
  val validParam = recalc(p)
  def businessMethod(i: Int): Int = i * validParam // OK
  def businessMethod(i: Int): Int = i * p // bloody wrong, "p" forbidden
  def recalc(p: Int) = ??? // some init pre-calc
}
```
Therefore:
- ``Rational`` primary constructor declared as ``private`` to prevent 
non-simplified rationals creation from clients code.
- Recursive ``GCD`` Rational creation algorithm called from companion 
``apply`` method.
- Two overloaded ``apply`` methods using initial one with subsequent
``GCD`` call.
- ``Rational`` class internals have access to normalized data only. 
Profit!!!
 
## Implicit conversions
Rationals syntax really great:
```scala
val m = (Rational(1, 2) min Rational(1, 5)
val res = Rational(2, 3) * Rational(1, 22) 
            + Rational(1, 2) / Rational(1, 6)   
```
But it may be verbose at some cases. Implicit conversions to the rescue!
It is enough to define implicit conversions object:
```scala
object RationalConversions {
  implicit def tuple2Rational(t: (Int, Int)): Rational = t match {
    case (n, d) => Rational(n, d)
  }
  implicit def int2Rational(i: Int): Rational = Rational(i)
}
```

And import it inside client code to comply with conversions awareness rule:
```scala
import RationalConversions._ // import at the same package level
```

After these steps we may run requests like:
```scala
val im = (2, 3) * (1, 22) + (1, 2) / (1, 6)
> im: org.bearmug.rationals.Rational = 100/33
```

## Compare with Java
Java class wit similar functional implemented and tested [here](src/main/java/org/bearmug/rationals/RationalJ.java)
As it is easy to see it is much more verbose, has no these features with 
implicit conversions and operators overloading. Things stay the same.
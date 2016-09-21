[![Build Status](https://travis-ci.org/bearmug/functional-sandbox.svg?branch=master)](https://travis-ci.org/bearmug/functional-sandbox) [![Coverage Status](https://coveralls.io/repos/github/bearmug/functional-sandbox/badge.svg?branch=5-rationals-problem---describe-and-re-use)](https://coveralls.io/github/bearmug/functional-sandbox?branch=5-rationals-problem---describe-and-re-use) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/b0b71d6e74b14b58baffafce3ef1d550)](https://www.codacy.com/app/pavel-fadeev/functional-sandbox?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=bearmug/functional-sandbox&amp;utm_campaign=Badge_Grade)
 
# Scala enhanced rational numbers
With great kickoff from [Programming in Scala](https://www.amazon.com/Programming-Scala-Updated-2-12/dp/0981531687) about [rationals](http://booksites.artima.com/programming_in_scala/examples/html/ch06.html)
I`m really interested if it is possible improve given Scala solution. And 
is it possible to find similar elegance inside Java version for this code?

## Default details - unchanged
A lot of stuff remaining from [canonical implementation](http://booksites.artima.com/programming_in_scala/examples/html/ch06.html). 
It contains regular arithmetic operations ``+, -, /, *``. 
Solution also has unary ``-`` and ``min/max`` functions to enable simple selections. 
Validation implemented by common ``Predef.require`` call as well.

## *equals* and *toString* overridden
Formatted ``toString`` looks a bit shorter than direct concatenation and served at compile time:
```scala
  override def toString: String = s"$n/$d"
```

And ``equals`` code simple now as 1-2-3. 
At the same time it keeps contract with comparison against ``null``s and wrong class instances. 
```scala
  override def equals(obj: scala.Any): Boolean = obj match {
    case o: Rational => this - o match { case Rational(on, _) => on == 0 }
    case _ => false
  }
```
Now ``Rational`` assertions concise and does not require ``toString`` usage:
````scala
  test("max operator works") {
    assert((Rational(1, 2) max Rational(1, 5)) == Rational(1, 2))
  }
```

## Compare rationals against each other
Arithmetic operations just implemented as defined into original, nothing interesting there. 
Here is the link to the [Rational.scala](src/main/scala/org/bearmug/rationals/Rational.scala) source to review. 

What is really exciting is rationals comparison operations. 
Those ones could be clean and self-explanatory:
```scala
  def >(o: Rational): Boolean = this - o match { case Rational(in, _) => in > 0 }
  def <(o: Rational): Boolean = this - o match { case Rational(in, _) => in < 0 }
  def >=(o: Rational): Boolean = this > o || this == o
  def <=(o: Rational): Boolean = this < o || this == o
```

## Rationals construction
As a very first step, [GCD](https://en.wikipedia.org/wiki/Greatest_common_divisor) moved to companion object. 
Our rational numbers have to be simplified with GCD, but if we`ll do this inside ``Rational`` 
class construction, there is a chance that we will use initial (before ``GCD`` ) values for subsequent calculations. 
Then it may looks like:
```scala
class SampleThing(p: Int) {
  val validParam = recalc(p) // object init
  def businessMethod(i: Int): Int = i * validParam // OK
  def businessMethod(i: Int): Int = i * p // bloody wrong, "p" may not be used
}
```
Therefore:
- ``Rational`` primary constructor now declared as ``private`` to prevent non-simplified rationals creation from clients code.
- Recursive ``GCD`` Rational creation algorithm called from companion ``apply`` method. 
Direct ``Rational`` creation could be done:
```scala
  cal r = Rational(1, 33) // create Rational over companion object 'apply' calll
```
- Two overloaded ``apply`` methods using initial one with subsequent ``GCD`` call. 
Methods look just perfect:
```scala
  def apply(n: Int): Rational = Rational(n, 1)
  def apply(t: (Int, Int)): Rational = t match { case (r, d) => Rational(r, d) }
```

From now ``Rational`` implementation internally restricted to use non-normalized values for ``number/denominator`` pair.
On another end ``Rational`` API users may feel them safer, since there are less chances to hit wrong call.
Profit!!!
 
## Rational implicit conversions
Rationals syntax really great:
```scala
val m = Rational(1, 2) min Rational(1, 5)
val res = Rational(2, 3) * Rational(1, 22) + Rational(1, 2) / Rational(1, 6)   
```
But it may be verbose at some cases. So... implicit conversions to the rescue!
It is enough to define implicit conversions object:
```scala
object RationalConversions {
  implicit def tuple2Rational(t: (Int, Int)): Rational = t match {
    case (n, d) => Rational(n, d)
  }
  implicit def int2Rational(i: Int): Rational = Rational(i)
}
```

...And import it inside client code to comply with conversions awareness rule:
```scala
import RationalConversions._ // import at the same package level
```

After these steps we may run requests like below and output will have type ``Rational`` number:
```scala
val im = (2, 3) * (1, 22) + (1, 2) / (1, 6)
> im: org.bearmug.rationals.Rational = 100/33
```

## Compare with Java
Java class with similar functional implemented and tested [here](src/main/java/org/bearmug/rationals/RationalJ.java)
Easy to see that Java code much more verbose, has no features with implicit conversions and operators overloading. 

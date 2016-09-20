[![Build Status](https://travis-ci.org/bearmug/functional-sandbox.svg?branch=master)](https://travis-ci.org/bearmug/functional-sandbox) [![Coverage Status](https://coveralls.io/repos/github/bearmug/functional-sandbox/badge.svg?branch=5-rationals-problem---describe-and-re-use)](https://coveralls.io/github/bearmug/functional-sandbox?branch=5-rationals-problem---describe-and-re-use) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/b0b71d6e74b14b58baffafce3ef1d550)](https://www.codacy.com/app/pavel-fadeev/functional-sandbox?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=bearmug/functional-sandbox&amp;utm_campaign=Badge_Grade)
 
# Playing around rational numbers
With great kickoff from [Programming in Scala](https://www.amazon.com/Programming-Scala-Updated-2-12/dp/0981531687) about [rationals construction](http://booksites.artima.com/programming_in_scala/examples/html/ch06.html)
I`m really interested if it is possible to implement similar stuff with
Java and may be do more with Scala concise.

## Default details
Implementation contains regular arithmetic operations ``+, -, /, *``. It
also has unary ``-`` and ``min/max`` functions to enable simple 
selections. Validation also implemented by common ``Predef.require``
call.

## Essentials
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

## Operations
Arithmetic operations just implemented as defined into original, nothing
interesting there. Here is the link to the [Rational.scala](src/main/scala/org/bearmug/rationals/Rational.scala) source to
review. 

Really exciting part is rationals coparison operations. Those ones clean
and self-explanatory:
```scala
  def >(o: Rational): Boolean = this - o match { case Rational(in, _) => in > 0 }
  def <(o: Rational): Boolean = this - o match { case Rational(in, _) => in < 0 }
  def >=(o: Rational): Boolean = this > o || this == o
  def <=(o: Rational): Boolean = this < o || this == o
```
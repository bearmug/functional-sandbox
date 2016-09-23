[![Build Status](https://travis-ci.org/bearmug/functional-sandbox.svg?branch=master)](https://travis-ci.org/bearmug/functional-sandbox) [![Coverage Status](https://coveralls.io/repos/github/bearmug/functional-sandbox/badge.svg?branch=master)](https://coveralls.io/github/bearmug/functional-sandbox?branch=master) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/b0b71d6e74b14b58baffafce3ef1d550)](https://www.codacy.com/app/pavel-fadeev/functional-sandbox?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=bearmug/functional-sandbox&amp;utm_campaign=Badge_Grade)
 
Some Scala-related articles and around. 

## Scala enhanced rational numbers 
With great kickoff from 
[Programming in Scala](https://www.amazon.com/Programming-Scala-Updated-2-12/dp/0981531687) 
book has very useful chapter about 
[rationals](http://booksites.artima.com/programming_in_scala/examples/html/ch06.html)
In a nutshell, it is related to rational numbers calculations, unavailable
with regular calculations.
With little enhancements it is possible to have client code like: 
```scala
val im = (2, 3) * (1, 22) + (1, 2) / (1, 6)
> im: org.bearmug.rationals.Rational = 100/33
```

Let`s look 
[inside enhancements...](docs/01-enhanced-rationals.md)

## Longest Increasing Subsequence with Scala
Longest Increasing Subsequence 
[problem](https://en.wikipedia.org/wiki/Longest_increasing_subsequence) 
is a well-known topic from dynamic programming domain. 
[Ignition article]
(http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/)
and same [hackerrank](https://www.hackerrank.com/challenges/longest-increasing-subsequent)
task did push me towards investigations for canonical Scala implementation.
Most of solutions I`ve found are simply written with imperative style.
Or have inacceptable time complexity like O(n^2) or worse. 
So, primary goal now is to see if pure functional solution could be short and efficient at the same time.
And... actually, is it much slower than vanilla Java version?

[It`s time to dive into details!](docs/02-algo-lis.md)
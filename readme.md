[![Build Status](https://travis-ci.org/bearmug/functional-sandbox.svg?branch=master)](https://travis-ci.org/bearmug/functional-sandbox) [![Coverage Status](https://coveralls.io/repos/github/bearmug/functional-sandbox/badge.svg?branch=master)](https://coveralls.io/github/bearmug/functional-sandbox?branch=master) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/b0b71d6e74b14b58baffafce3ef1d550)](https://www.codacy.com/app/pavel-fadeev/functional-sandbox?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=bearmug/functional-sandbox&amp;utm_campaign=Badge_Grade)
 
Some Scala-related articles and around. 

## Scala enhanced rational numbers 
With great kickoff from 
[Programming in Scala](https://www.amazon.com/Programming-Scala-Updated-2-12/dp/0981531687) 
about [rationals]
(http://booksites.artima.com/programming_in_scala/examples/html/ch06.html)
I`m really interested if it is possible improve given Scala solution. 
And may we hope for similar elegance inside Java version for this code?

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

[It`s time to dive into details!](docs/02-algo-lis.md)

## FunFin building
Let`s see how much fun could be derived from financial Scala application development.
Assuming to achieve it step-by-step, with small increments.
Side idea is to track progress over ticketing system and estimate efforts with number of Pomodoro's spent.

[Link to presumed articles series](https://github.com/bearmug/fun-fin)
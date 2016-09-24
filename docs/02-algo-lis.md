# Longest Increasing Subsequence with Scala
Longest Increasing Subsequence 
[problem](https://en.wikipedia.org/wiki/Longest_increasing_subsequence) 
is a well-known topic from dynamic programming domain. 
[Ignition article]
(http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/)
and same [hackerrank](https://www.hackerrank.com/challenges/longest-increasing-subsequent)
task did push me towards investigations for canonical Scala implementation.
Most of solutions I`ve found are simply written with imperative style.
Either have inacceptable time complexity like O(n^2) or worse. 
So, primary goal now is to see if pure functional solution could be short and efficient at the same time.
And... is it much slower than vanilla Java version?

## Implementation idea
The pre-conception is to create pure functional solution. 
Saying no to variables, functions with side-effects, imperative loops,  etc..
Next level is to achieve proper time complexity over existing Scala APIs.
There is no need to re-invent binary search wheel for this, 
there should be built-in toolkit for such a purpose.

## Narrow down solution scope
Perfect scenario is when solution class has single available method or property.
This property supposed to be used as main class action. Let`d do it like:
```scala
class LISubSequence(l: List[Int]) {
  lazy val length = { ... } // no public logic outside this block
} 
```
Value laziness let us postpone expensive calculation until the moment when 
it is really required.

## Data structures to iterate over solution search
Solution upper-level logic just taken from this [example]
(http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/).
In short, this algorithm scan incoming elements one-by-one and:
 - find longest subsequence A to append new element
 - increment given subsequence A
 - check if there are subsequence with same length B and purge the one with 
 biggest element at the tail.

Known solution build elements sequences. These sequences kept sorted by their lengths.
At the end of solution it is enough just take last sequence and return it`s 
length as a result. Intermediate data structure may look like this:
```
val[0] = [2]
val[1] = [1, 4, 5]
val[2] = [3, 5, 8, 9]
```
It appears that given algorithm calculates two parameters for each subsequence:
 - total elements number for given subsequence
 - max subsequence element
So why not to store this values tuple for each of elements above
```scala
type Pair = (Int, Int)
``` 

This case simplest solution for input ``3, 1, 2, 4, 5`` will look like:
```scala
// put initial element
res0 = ((1,3)) // subsequence with length == 1 and max element == 3

// keep going with next item
res1 = ((1,1))

// put 2
res2 = ((2,2), (1,1))

// 4
((3,4), (2,2), (1,1))

```
 
## Recursive subsequence scan
The high-level idea is to iterate over input list one-by-one.
This way we may accumulate output (let`s ignore question how for now)
inside some of passed parameters. Sounds like tail-recursion opportunity!
```scala
  @tailrec
  def process(numbers: List[Int], seqs: TreeSet[Pair]): TreeSet[Pair] = numbers match {
    case Nil => seqs
    case x :: xs => process(xs, appendSeq(seqs, x))
  }
```
This is a classic approach: trim input ``numbers``, accumulate output inside
``seqs`` and pass ``seqs`` as a result once ``numbers`` got empty. 
One unclear point is how we accumulate output inside ``appendSeq`` call. 
Keep reading!

## Solution set increment
### With direct O(n*n) time complexity
### Improvement with O(n*log(n)) time complexity

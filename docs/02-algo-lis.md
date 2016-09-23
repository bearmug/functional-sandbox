# Longest Increasing Subsequence with Scala
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

## Implementation idea
The pre-conception is to create pure functional solution. 
Saying no to variables, functions with side-effects, imperative loops,  etc..
Next level is to achieve proper time complexity over existing Scala APIs.
There is no need to re-invent binary search wheel for this, 
there should be built-in toolkit for such a purpose.

## General design for solution class
Surely solution class supposed to be immutable and with crystal-clear API.
Perfect scenario is when solution class has single available method or property.
This property supposed to be used as main class action.

## Data structure to iterate over solution search
It appears that given algorithm calculates two parameters for each subsequence:
 - max subsequence element
 - total elements number for given subsequence
Direct implementation version may keep subsequences full-size:
```scala
type SubSeq = List[Int]
```
But it means we have to store full set of input collection/array elements.
Much more efficient to replace full subsequence with pair of:
 - subsequence length
 - subsequence max element
```scala
type Pair = (Int, Int)
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

# Longest Increasing Subsequence with Scala
Longest Increasing Subsequence 
[problem](https://en.wikipedia.org/wiki/Longest_increasing_subsequence) 
is a well-known topic from dynamic programming domain. 
[Ignition article]
(http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/)
and same [hackerrank](https://www.hackerrank.com/challenges/longest-increasing-subsequent)
task did push me towards investigations for canonical Scala implementation.
Most of solutions I`ve found are simply written with imperative style.
Either have inacceptable time complexity like O(n^2) or worse. 
So, primary goal now is to see if pure functional solution could be short and efficient at the same time.

## Implementation idea
The pre-conception is to create pure functional solution. 
Saying no to variables, functions with side-effects, imperative loops,  etc..
Next level is to achieve proper time complexity over existing Scala APIs.
No need to re-invent binary search wheel for this, 
there should be built-in toolkit for such a purpose.

## Solution path and data structures
Solution upper-level logic just taken from this [example]
(http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/).
In short, this algorithm scans incoming elements one-by-one and:
 - if input element is the smallest one across existing subsequences tails - 
 just create new subsequence with length 1 for this element. 
 Existing subsequence with length 1 to be removed 
 - if next element is the greatest one across existing subsequences tails - 
 clone longest existing subequence and increment it with given element
 - otherwise clone subsequence with tail closest to input element and 
 increment it with this element. New subsequence with length N discards
 other subsequences with the same length

### Classic approach with full subsequences tracking
Solution for input ``4, 1, 3, 2, 5`` will look like:
```scala
// put initial element
((4)) // 

// keep going with next item
((1)) // put '1' to new element with length == 1 and remove (4) as two 
sequences have same length

// put 3
((1), (1,3)) // 3 is the biggest number agross existing subsequences tails, 

// 2
((1), (1,2)) //(1) found, cloned and transformed to (1,2), (1,3) removed 
as duplicate for length 2 

// 5
((1), (1,2), (1,2,5)) // 5 is the greatest across existing subsequences
// therefore new subsequence generated from (1,2)
```
 
### Cleanup redundancy from subsequence description type 
But why track full subsequence, looks like an overkill. 
It is enough to store each subsequence latest element and it`s length only.
Defining type shortcut for subsequence desciption:
```scala
type P = (Int, Int) // (length, max value) pair
```
Then solution for ``4, 1, 3, 2, 5`` to be represented as:
```scala
// 4
((1,4))

// 4, 1
((1,1))

// 4, 1, 3
((2,3), (1,1))

// 4, 1, 3, 2
((2,2), (1,1))

// 4, 1, 3, 2, 5
((3,5), (2,2), (1,1))
```
 
## Recursive subsequence scan
The high-level idea is to iterate over input list one-by-one.
This way we may accumulate output inside some of recursively passed parameters. 
Sounds like tail-recursion opportunity!
```scala
  @tailrec
  def process(numbers: List[Int], seqs: TreeSet[P]): TreeSet[P] = numbers match {
    case Nil => seqs
    case x :: xs => process(xs, appendSeq(seqs, x))
  }
```
This is a classic approach: trim input ``numbers``, accumulate output inside
``seqs`` and pass ``seqs`` as a result once ``numbers`` got empty. 
One unclear point is how we accumulate output inside ``appendSeq`` call. 

Why result accumulator is a ``TreeSet``? 
First, longest subsequence could be taken by O(1) from top of sorted set on 
algorithm completion.
Second, it is the way to convert O(n * n) time complexity to O(n * log(n)) 
because linear search to be converted to binary search.

## Specific item processing
Now we need implement ``appendSeq`` to put next input element to the proper subsequence.
So the goal could be decomposed to:
- find subsequence with last number which is closest to our input
- clone subsequence, increment it, get rid of subsequences with same length if required

### O(n * n) complexity
First proposal could be like simple closest element lookup with ``find``.
```scala
  def appendSeq(seqs: TreeSet[SeqTop], x: Int): TreeSet[SeqTop] = {
    val s = seqs.find((t: SeqTop) => t._2 < x)
    s match {
      case None => seqs + ((1, x))
      case Some(e) => seqs - e + ((e._1 + 1, x))
    }
  }
```
But it doesn`t work well, since it has iterator inside with linear lookup logic.
Plus we have to think about elimination for subsequences with same length.
Much better it works with code snippet from below:
```scala
  def appendSeq(seqs: TreeSet[P], x: Int): TreeSet[P] = {
    val s = seqs.to((Integer.MAX_VALUE, x)).toList
    s match {
      case Nil => seqs.headOption match {
        case None => seqs + ((1, x))
        case Some(h) => seqs + ((h._1 + 1, x))
      }
      case e => seqs - e.last + ((e.last._1, x))
    }
  }
```
It is optimized with lookup over ``to`` call.
But still we do linear [stuff]
(https://github.com/scala/scala/blob/v2.10.3/src/library/scala/collection/LinearSeqOptimized.scala#L134) 
while searching for subsequence with same length to eliminate.

### Improvement with O(n*log(n)) time complexity
Final subsequence lookup/replacement version provided into this section.
Now we using search with ``to`` call and at the same time we 
remove redundant elements with ``lastOption`` optimized version.
```scala
  def appendSeq(seqs: TreeSet[P], x: Int): TreeSet[P] = {
    val s = seqs to((Integer.MAX_VALUE, x))
    s match {
      case e if e.isEmpty => seqs headOption match {
        case None => seqs + ((1, x))
        case Some((hl, _)) => seqs + ((hl + 1, x))
      }
      case e => e.lastOption match {
        case None => seqs + ((1, x))
        case Some((ll, lm)) => seqs - ((ll, lm)) + ((ll, x))
      }
    }
  }
```

## Narrow down solution scope
Perfect scenario is when solution class has single available method or property.
This property supposed to be used as a main class feature. Let`d do it like:
```scala
class LISubSequence(l: List[Int]) {
  lazy val length = { // delayed execution
  
    ... // internal class defs here 
  
    process(l, TreeSet.empty(ord)) toList match {
      case Nil => 0
      case (len, max) :: ls => len
    }
  } // no public logic outside this block
} 
```
Value laziness let us postpone expensive calculation until the moment when 
it is really required.

## Summary
So, in general Scala solution is really elegant and takes less than 40 LOC for class code itself.
It is possible shorten it even more, but then code readability may really suffer.
You may review final solution [here]
(../src/main/scala/org/bearmug/algo/LISubSequence.scala#L11) 
and see some tests for it under [related suite]
(../src/test/scala/org/bearmug/algo/LISubSequenceSuite.scala) .
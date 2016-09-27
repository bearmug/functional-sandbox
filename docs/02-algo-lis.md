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
((1), (1,2), (1,2,5)) //5 is the greatest across existing subsequences
```
 
 But why track full subsequence, looks like an overkill. 
 it is enough to store each subsequence latest element and lengthm. 
 
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

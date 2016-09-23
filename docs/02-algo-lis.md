# Longest Increasing Subsequence with Scala
Longest Increasing Subsequence 
[problem](https://en.wikipedia.org/wiki/Longest_increasing_subsequence) 
is a well-known topic from dynamic programming domain. 
[Ignition article]
(http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/)
and same [hackerrank](https://www.hackerrank.com/challenges/longest-increasing-subsequent)
task did push me towards investigations for canonical Scala implementation.
Most of solutions I`ve found are simply written with imperative style.
Or have inacceptable time coplexity like O(n^2). 
So, primary goal now is to see if pure functional solution could be short and efficient.

## Implementation idea
### General design for solution class
### Data structure to iterate over solution search
### Recursive subsequence scan
### Solution set increment
#### With direct O(n*n) time complexity
#### Improvement with O(n*log(n)) time complexity

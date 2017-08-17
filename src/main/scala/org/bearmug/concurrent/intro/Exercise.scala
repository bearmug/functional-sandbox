package org.bearmug.concurrent.intro

import scala.annotation.tailrec

object Exercise {

  /*
  Implement compose method. This method should return a function, which is
  the composition of input functions f and g.
   */
  def compose[A, B, C](g: B => C, f: A => B): A => C = x => g(f(x))

  /*
  Implement a fuse method. The resulting Option object should contain a tuple
  of values from the Option objects a and b, given that both a and b are
  non-empty. Use for-comprehensions.
   */
  def fuse[A, B](a: Option[A], b: Option[B]): Option[(A, B)] =
    for {
      valA <- a
      valB <- b
    } yield (valA, valB)

  /*
  Implement a check method. The method must return true if and only if the pred
  function returns true for all the values in xs without throwing an exception.
   */
  def checkApi[T](xs: Seq[T])(pred: T => Boolean): Boolean = xs.forall(pred)

  /*
  Implement check method with no Seq API calls
   */
  @tailrec
  def check[T](xs: Seq[T])(pred: T => Boolean): Boolean = xs match {
    case Nil => true
    case h :: tail => pred(h) && check(tail)(pred)
  }

  /*
  Implement permutations function, which, given a string, returns a sequence of
  strings that are lexicographic permutations of input string
   */
  def permutationsApi(x: String): Seq[String] = x.permutations.toSeq.sorted

  /*
  Implement permutations with no SeqLike API calls
   */
  def permutations(x: String): Seq[String] = ???
}

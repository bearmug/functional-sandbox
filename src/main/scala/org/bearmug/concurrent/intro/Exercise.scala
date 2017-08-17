package org.bearmug.concurrent.intro

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

}

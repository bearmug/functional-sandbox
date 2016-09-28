package org.bearmug.algo

import scala.annotation.tailrec
import scala.collection.immutable.TreeSet

/**
  * Class to see if Scala code for
  * [[https://en.wikipedia.org/wiki/Longest_increasing_subsequence LIS]]
  * could be clean and efficient.
  */
class LISubSequence(l: List[Int]) {

  type P = (Int, Int) // (length, max value) pair

  implicit val ord: Ordering[P] = new Ordering[P] {
    override def compare(x: P, y: P): Int = (x, y) match {
      case ((_, xt), (_, yt)) => yt compareTo xt
    }
  }

  lazy val length = {
    @tailrec
    def process(numbers: List[Int], seqs: TreeSet[P]): TreeSet[P] = numbers match {
      case Nil => seqs
      case x :: xs => process(xs, appendSeq(seqs, x))
    }

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

    process(l, TreeSet.empty(ord)) toList match {
      case Nil => 0
      case (len, max) :: ls => len
    }
  }
}

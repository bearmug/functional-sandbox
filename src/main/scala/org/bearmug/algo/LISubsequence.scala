package org.bearmug.algo

import scala.annotation.tailrec
import scala.collection.immutable.TreeSet

/**
  * Class to see if Scala code for [[https://en.wikipedia.org/wiki/Longest_increasing_subsequence LIS]]
  * could be clean and efficient.
  */
class LISubsequence(l: List[Int]) {

  type Pair = (Int, Int) // (length, max value) pair

  implicit val ord: Ordering[Pair] = new Ordering[Pair] {
    override def compare(x: Pair, y: Pair): Int = (x, y) match {
      case ((_, xt), (_, yt)) => yt compareTo xt
    }
  }

  lazy val length = findLength()

  def findLength(): Int = {
    @tailrec
    def process(numbers: List[Int], seqs: TreeSet[Pair]): TreeSet[Pair] = numbers match {
      case Nil => seqs
      case x :: xs => process(xs, appendSeq(seqs, x))
    }

    def appendSeq(seqs: TreeSet[Pair], x: Int): TreeSet[Pair] = {
      val s = seqs to((Integer.MAX_VALUE, x))
      s match {
        case e if e.isEmpty => seqs headOption match {
          case None => seqs + ((1, x))
          case Some(h) => seqs + ((h._1 + 1, x))
        }
        case e => seqs - e.last + ((e.last._1, x))
      }
    }

    process(l, TreeSet.empty(ord)) toList match {
      case Nil => 0
      case ls => ls map (_._1) max
    }
  }
}

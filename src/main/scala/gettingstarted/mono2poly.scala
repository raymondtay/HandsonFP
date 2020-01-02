package gettingstarted

// Abstracting over the type !
//
object MyTransformation {

  // The details of the algorithm is uninteresting - important thing to keep in
  // mind is that the algorithm is trying to find a match and returns the index
  // of the element where it's found or (-1).
  def findFirst(xs: Array[String], key: String) : Int = {
    @annotation.tailrec
    def loop(n : Int) : Int =
      if (n >= xs.length) - 1
      else if (xs(n) == key) n
      else loop(n+1)

    loop(0)
  }

  def findFirst[A](xs: Array[A], key: A) : Int = {
    @annotation.tailrec
    def loop(n : Int) : Int =
      if (n >= xs.length) - 1
      else if (xs(n) == key) n
      else loop(n+1)

    loop(0)
  }

  // Exercise 1: Implement the `isSorted` function that checks whether an
  // Array[A] is sorted according to a given comparison function.
  def isSorted[A](xs: Array[A], ordered: (A, A) => Boolean) : Boolean = ???

  def main(args: Array[String]) = {
    println(findFirst(Array("hi", "there"), "hi"))
    println(findFirst(Array("hi", "there"), ""))
    println(findFirst[String](Array("hi", "there"), ""))
  }

}


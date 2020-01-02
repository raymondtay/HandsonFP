package gettingstarted

object MyPartials {

  // this function takes a value and a function of 2 arguments, and returns a
  // function of 1 argument as its result. The name comes from the fact that
  // the function is being applied to some but not all of the arguments it
  // requires


  // Exercise 1: Complete the function's implementation
  def partial1[A,B,C](a: A, f: (A, B) => C) : B => C = ???

  val + : (Int, Int) => Int = ???

  def main(args: Array[String]) : Unit = {
    partial1[Int,Int,Int](1, (+)) // A runtime error will be thrown if you did not implement this correctly.
  }

}


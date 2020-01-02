package gettingstarted

object Exercises {

  // Exercise 1: Write this implementation
  def curry[A,B,C](f: (A, B) => C) : A => (B => C) = ???

  // Exercise 2: Implement uncurry, which reverses the transformation of curry.
  // Take note that since => associates to the right, A => (B => C) can be
  // written as A => B => C
  def uncurry[A,B,C](f: A => B => C) : (A, B) => C = ???

  // implement the higher-order function that composes two functions.
  def compose[A,B,C](f: B => C, g : A => B) : A => C = ???

}



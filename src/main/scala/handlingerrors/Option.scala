package handlingerrors

import scala.{Option => _, _} // hiding the Option type but import everything else.


// Option is a datatype that represents that there could be a value or not.
// Following this intuition, make an attempt and implement these functions 
//
sealed trait Option[+A] { self => 
  // Exercise 1: implement a `map`-ing function that transform an Optional
  // value - remember to take into account that its either something or nothing
  // at all.
  def map[B](f: A => B) : Option[B] = ???

  // Exercise 2: implement the flatMap.
  def flatMap[B](f: A => Option[B]) : Option[B] = ???

  // Exercise 3: implement the `getOrElse` function which returns the default
  // value if its nothing else return the actual value
  def getOrElse[B >: A](default: => B) : B = ???

  // Exercise 4: implement the `orElse` function which returns this value (when
  // nothing) else the given value
  def orElse[B >:A](ob: => Option[B]) : Option[B] = ???

  // Exercise 5: implement the `filter` function which returns this value if
  // the predicate is successful otherwise nothing.
  def filter(f: A => Boolean) : Option[A] = ???

  // Exercise 6: implement `map2` that combines two Option values using a
  // binary function. If tierh Option value is Nothing, then the return value
  // is too.
  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C) : Option[C] = ???

  // Exercise 7: implement `sequence` that traverses the list of optional
  // values and collects them.
  def sequence[A](os: List[Option[A]]) : Option[List[A]] = ???

}

case class Something[+A](value: A) extends Option[A]
case object NotAThing extends Option[Nothing]


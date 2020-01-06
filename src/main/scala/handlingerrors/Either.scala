package handlingerrors

import scala.{Either => _, _}

// Either data type represents values that be one of two things. They are also
// known as disjoint union of two types. By convention, the `Right` values
// indicate good conditions or successes whilst the `Left` values represents
// everything else.
//
sealed trait Either[+E,+A] { self =>

  // implement all the functions given below
  //
  def map[B](f: A => B) : Either[E, B] = ???

  def flatMap[EE >: E, B](f: A => Either[EE, B]) : Either[EE, B] = ???

  def orElse[EE >: E, B >: A](b: => Either[EE, B]) : Either[EE, B] = ???

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C) : Either[EE, C] = ???

  def sequence[E, A](es: List[Either[E, A]]) : Either[E, List[A]] = ???

  def traverse[E, A, B](as: List[A])(f: A => Either[E, B]) : Either[E, List[B]] = ???

}

case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]



package datastructures

// This is how we can typically encode a Abstract Data Type in Scala.
// In particular, this is a singly-linked list aka List
trait List[+A] // adding 'sealed' means all implementations of List must be in this file.
case object EmptyList extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def sum(is : List[Int]) : Int = is match { // pattern matching
    case EmptyList => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(is : List[Int]) : Int = is match { // pattern matching
    case EmptyList => 1
    case Cons(x, xs) => x * product(xs)
  }

  // Remember that `apply` is sugar syntax for method invocation in Scala.
  // In this case, its aka a variadic function because it consumes many
  // arguments (indicated by "A*") ; the special type annotation "_*" allows us
  // to pass a sequence to a variadic function w/o harrassment from the
  // compiler.
  def apply[A](xs: A*) : List[A] = 
    if (xs.isEmpty) EmptyList
    else Cons(xs.head, apply(xs.tail:_*))

  // This implementation only starts copying values until the first list is
  // exhausted.
  def append[A](l: List[A], r: List[A]) : List[A] = l match {
    case EmptyList => r
    case Cons(h, t) => Cons(h, append(t, r))
  }

  // Reversing a list
  def reverse[A](xs: List[A]) : List[A] = xs match {
    case EmptyList => xs
    case Cons(h, t) => append(reverse(t), Cons(h, EmptyList))
  }

  // Exercise 1 : implement a function `setHead` for replacing the first
  // element of the list with a different value
  def setHead[A](xs : List[A], value: A) : List[A] = xs match {
    case EmptyList => Cons(value, EmptyList)
    case xs@Cons(h, t) => Cons(value, xs)
  }

  // Exercise 2 : implement the function `drop` which removes the first `n`
  // elements from a list.
  def drop[A](xs : List[A], n: Int) : List[A] = xs match {
    case EmptyList => xs
    case Cons(h, t) if n > 0 => drop(t, n-1)
    case _ => xs
  }

  // Exercise 3 : implement the function `dropWhile` which removes elements
  // from the List prefix as long as they match a predicate.
  def dropWhile[A](xs : List[A], f : A => Boolean) : List[A] = xs match {
    case EmptyList => xs
    case Cons(h, t) if f(h) => dropWhile(t, f)
    case Cons(h, t@Cons(hh, tt)) => Cons(h, dropWhile(t, f))
  }

  // Exercise 4 : implement the function `init` which returns all elements of
  // the given list except the first.
  def init[A](xs: List[A]) : List[A] = xs match {
    case EmptyList => xs
    case Cons(h, t) => t
  }

  // Exercise 5 : implement foldRight which applies a binary function to each
  // element; starting from the element to the first
  @annotation.tailrec
  def foldRight[A,B](xs: List[A], z : B)(f: (A, B) => B) : B = xs match {
    case EmptyList => z
    case Cons(h, t) => foldRight(t, f(h,z))(f)
  }

  // Exercise 6 : Rewrite 'sum' and 'product' interms of 'foldRight'.
  def sum2(xs: List[Int]) = foldRight(xs, 0)(_ + _)
  def product2(xs: List[Int]) : Int = foldRight(xs, 1)(_ * _)

  // Exercise 7 : implement `length` which calculates the length of any list
  //              using `foldRight` only.
  def length[A](xs: List[A]) : Int = foldRight(xs, 0)((_, acc) => acc + 1)

  // Exercise 8 : implement `foldLeft` which has the same equivalence as
  // `foldRight` but take note that its now left thru right.
  @annotation.tailrec
  def foldLeft[A,B](xs: List[A], z: B)(f: (B, A) => B) : B = xs match {
    case EmptyList => z
    case Cons(h, t) => foldLeft(t, f(z, h))(f)
  }

  // Exercise 9 : Rewrite 'sum' and 'product' interms of 'foldLeft'.
  def sum3[A](xs: List[A]) : Int = foldLeft(xs, 0)((acc, _) => acc + 1)
  def product3[A](xs: List[A]) : Int = foldLeft(xs, 1)((acc, _) => acc * 1)

}



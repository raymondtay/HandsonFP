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

  def product(is : List[Int]) : Double = is match { // pattern matching
    case EmptyList => 1.0
    case Cons(x, xs) => x * sum(xs)
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

  // Exercise 1 : implement a function `setHead` for replacing the first
  // element of the list with a different value
  def setHead[A](xs : List[A], value: A) : List[A] = ???

  // Exercise 2 : implement the function `drop` which removes the first `n`
  // elements from a list.
  def drop[A](xs : List[A], n: Int) : List[A] = ???

  // Exercise 3 : implement the function `dropWhile` which removes elements
  // from the List prefix as long as they match a predicate.
  def dropWhile[A](xs : List[A], f : A => Boolean) : List[A] = ???

  // Exercise 4 : implement the function `init` which returns all elements of
  // the given list except the first.
  def init[A](xs: List[A]) : List[A] = ???

  // Exercise 5 : implement foldRight which applies a binary function to each
  // element; starting from the element to the first
  def foldRight[A,B](xs: List[A], z : B)(f: (A, B) => B) : B = ???

  // Exercise 6 : Rewrite 'sum' and 'product' interms of 'foldRight'.
  def sum2(xs: List[Int]) = ???
  def product2(xs: List[Int]) : Int = ???

  // Exercise 7 : implement `length` which calculates the length of any list
  //              using `foldRight` only.
  def length[A](xs: List[A]) : Int = ???

  // Exercise 8 : implement `foldLeft` which has the same equivalence as
  // `foldRight` but take note that its now left thru right.
  def foldLeft[A,B](xs: List[A], z: B)(f: (B, A) => B) : B = ???

  // Exercise 9 : Rewrite 'sum' and 'product' interms of 'foldLeft'.
  def sum3[A](xs: List[A]) : A = ???
  def product3[A](xs: List[A]) : A = ???

}



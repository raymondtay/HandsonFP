package datastructures

object Exercises {

  // convenience functions
  def flip[a, b]: (b => a => b) => a => b => b = f => x => y => f(y)(x)
  def flip2[a, b]: (a => b => b) => b => a => b = f => x => y => f(y)(x)

  // Exercise 1 : Can you figure out a way generalize the `sum` and `product`
  //              functions you saw in List.scala ?
  def generalSumAndProduct[A](xs: List[A], z: A )(f: (A, A) => A) : A = ???

  // Exercise 2 : implement `append` interms of either `foldRight` or
  // `foldLeft`
  def append[A](xs: List[A], ele: A) : List[A] = ???

  // Exercise 3 : implement a pure function that adds the value to each element
  // of the list
  def addOne(xs: List[Int]) : List[Int] = ???

  // Exercise 4 : generalize the application of a function to each element of
  // the list
  def map[A,B](xs: List[A])(f: A => B) : List[B] = {
    def go(as: List[A], bs: List[B]) : List[B] = 
      as match {
        case EmptyList => bs
        case Cons(h, t) => go(t, Cons(f(h), bs))
      }
    go(xs, EmptyList)
  }


  // Exercise 5 : implement a function filter that removes elements from a list
  // unless they satisfy the predicate
  def filter[A](xs: List[A])(f: A => Boolean) : List[A]= ???

  // ------------ bonus questions below ---- a bit of a brain twister


  // Bonus Exercise 1 : implement `foldRight` interms of `foldLeft`
  def foldRight[A,B](xs: List[A], z: B)(f: (A, B) => B) : B = ???

  // Bonus Exercise 2 : implement `foldLeft` interms of `foldRight`
  def foldLeft[A,B](xs: List[A], z: B)(f: (B, A) => B) : B = ???

}



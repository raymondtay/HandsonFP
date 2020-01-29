package datastructures

object Exercises {

  // convenience functions
  def flip[a, b]: (b => a => b) => a => b => b = f => x => y => f(y)(x)
  def flip2[a, b]: (a => b => b) => b => a => b = f => x => y => f(y)(x)

  // Exercise 1 : Can you figure out a way generalize the `sum` and `product`
  //              functions you saw in List.scala ?
  def generalSumAndProduct[A](xs: List[A], z: A )(f: (A, A) => A) : A =
    foldLeft(xs, z)(f)

  // Exercise 2 : implement `append` interms of either `foldRight` or
  // `foldLeft`
  def append[A](xs: List[A], ele: A) : List[A] = List.append(Cons(ele, EmptyList), xs)

  // Exercise 3 : implement a pure function that adds the value to each element
  // of the list
  def addOne(xs: List[Int]) : List[Int] = map(xs)(_ + 1)

  // Exercise 4 : generalize the application of a function to each element of
  // the list
  def map[A,B](xs: List[A])(f: A => B) : List[B] = xs match {
    case EmptyList => EmptyList
    case Cons(h, t) => Cons(f(h), map(t)(f))
  }

  // Exercise 5 : implement a function filter that removes elements from a list
  // unless they satisfy the predicate
  def filter[A](xs: List[A])(f: A => Boolean) : List[A]= List.dropWhile(xs, f) 

  // ------------ bonus questions below ---- a bit of a brain twister


  // Bonus Exercise 1 : implement `foldRight` interms of `foldLeft`
  def foldRight[A,B](xs: List[A], z: B)(f: (A, B) => B) : B =
    foldLeft(xs, z)(Function.uncurried(flip2((f(_,_)).curried)))

  // Bonus Exercise 2 : implement `foldLeft` interms of `foldRight`
  def foldLeft[A,B](xs: List[A], z: B)(f: (B, A) => B) : B =
    foldRight(xs, z)(Function.uncurried(flip((f(_,_)).curried)))

}



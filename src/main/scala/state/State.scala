package state

// State represents the idea of encapsulating a function that describes a
// computation that consumes a state object and returns a 2-tuple which
// consists of (value derived using the state object, next "state")
//
// We encode this idea using this function type signature: s => (a, s)
//
// 
//

// Defines a behavior we wish.
trait Ticker {
  def tick() : (Int, Ticker)
}
// Defines a model that implements the behavior we wish.
case class Model(current : Int, previous : Int) extends Ticker {

  def newTicker = Model(current + 1, current)

  def tick() : (Int, Ticker) = {
    (previous, newTicker)
  }
}

object TickerOps {

  // The approach taken by `Ticker` is far more common than you initially
  // thought ☺
  //

  type Counter[+A] = Ticker => (A, Ticker)

  // function which allows us to lift a value into the type
  def unit[A](a: A) : Counter[A] = tkr => (a, tkr)

  // function which allows us to transform the value without changing the
  // container aka structure preserving transformation
  def map[A,B](s: Counter[A])(f: A => B) : Counter[B] =
    tkr => {
      val (a, tkr1) = s(tkr)
      (f(a), tkr1)
    }

  // Given a ticker and the number of times i like it to "tick", now 
  // we can control how many times the ticker will tick
  def ticktok(times: Int)(ticker: Ticker) : (List[Int], Ticker) = {
    if (times == 0) (List(0), ticker)
    else {
      val (x, s1) = ticker.tick
      val (y, s2) = ticktok(times - 1)(s1) 
      (x :: y, s2)
    }
  }

  // Exercise 1: implement `flatMap`
  def flatMap[A,B](f: Counter[A])(g: A => Counter[B]): Counter[B] =
    tkr => {
      val (a, tkr1) = f(tkr)
      g(a)(tkr1)
    }

  // Exercise 2: implement `map2` which combines 2 state actions into one.
  //             if you have attempted the previous exercises, this should not
  //             be unfamiliar 
  def map2[A,B,C](ca: Counter[A], cb: Counter[B])(f: (A,B) => C) : Counter[C] = ???

  // this is an example of a higher-combinator where we build further
  // abstractions using a simpler abstraction.
  def both[A,B](a: Counter[A], b: Counter[B]): Counter[(A, B)] = 
    map2(a, b)((_ , _))

  // Exercise 3: implement `sequence` which evaluates each monadic action
  // in the structure from left to right, and collect the results.
  def sequence[A](fs: List[Counter[A]]) : Counter[List[A]] = ???

  // ------ Bonus exercises --------------- //

  // Bonus exercise 1: reimplement `map` interms of `flatMap`.
  def mapB[A,B](s: Counter[A])(f: A => B) : Counter[B] = ???

  // Bonus exercise 2: reimplement `map2` interms of `flatMap`.
  def map2B[A,B,C](a: Counter[A], b: Counter[B])(f: (A,B) => C) : Counter[C] = ???
}

// Generalize it
case class State[S,+A](run: S => (A, S)) {

  // Exercise 1: Generalize the functions `unit`, `map`, `map2`, `flatMap`
  def map[B](f: A => B) : State[S, B] = ???

  // Setting a state
  def set[S](s: S): State[S, Unit] = ???

  // Getting the state
  def get[S] : State[S,S] = ???

  // Modify the state
  def modify[S](f: S => S): State[S, Unit] = ???

  def flatMap[B](f: A => State[S,B]) : State[S,B] = ???

  def map2[B,C](s: State[S, B])(f: (A, B) => C) : State[S,C] = ???
}

// @see [companion object in Scala](https://docs.scala-lang.org/overviews/scala-book/companion-objects.html)
object State {

  def unit[S,A](a: A) : State[S,A] = ???

}


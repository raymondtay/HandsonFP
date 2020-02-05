package state

// State represents the idea of encapsulating a function that describes a
// computation that consumes a state object and returns a 2-tuple which
// consists of (value derived using the state object, next "state")
//
// We encode this idea using this function type signature: s => (a, s)
//
// 
//


case class OOPTick() {
  // proper encapsulation
  private var previousCounter = 0
  private var currentCounter = 0
  // "getters"
  def getCurrent = currentCounter
  def getPrevious = previousCounter
  // "setters"
  def tick() = { previousCounter = currentCounter; currentCounter += 1}
}

object MyTickCounter {

  // A simple counter but limited in prowess as compare to `OOPTick`.
  def tick(c: Int) = (c, c+1) // type signature is Int => (Int, Int)

  // The approach taken by `OOPTick` is far more common than you initially
  // thought ☺
  //

  trait Ticker {
    def nextTick() : (Int, Ticker)
  }
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

  // Exercise 1: implement `flatMap`
  def flatMap[A,B](f: Counter[A])(g: A => Counter[B]): Counter[B] =
    tkr => {
      val (a, tkr1) = f(tkr)
      g(a)(tkr1)
    }

  // Exercise 2: implement `map2` which combines 2 state actions into one.
  //             if you have attempted the previous exercises, this should not
  //             be unfamiliar 
  def map2[A,B,C](a: Counter[A], b: Counter[B])(f: (A,B) => C) : Counter[C] = ???

  // this is an example of a higher-combinator where we build further
  // abstractions using a simpler abstraction.
  def both[A,B](a: Counter[A], b: Counter[B]): Counter[(A, B)] = 
    map2(a, b)((_ , _))

  // Exercise 3: implement `sequence`
  def sequence[A](fs: List[Counter[A]]) : Counter[List[A]] = ???

  // ------ Bonus exercises --------------- //

  // Bonus exercise 1: reimplement `map` interms of `flatMap`.
  def mapB[A,B](s: Counter[A])(f: A => B) : Counter[B] = ???

  // Bonus exercise 2: reimplement `map2` interms of `flatMap`.
  def map2B[A,B,C](a: Counter[A], b: Counter[B])(f: (A,B) => C) : Counter[C] = ???
}

// Generalize it
case class State[S,+A](run: S => (A, S)) { self =>

  // Exercise 1: Generalize the functions `unit`, `map`, `map2`, `flatMap`
  def map[B](f: A => B) : State[S, B] =
    State{(s:S) => 
      val (a,s2) = self.run(s)
      (f(a), s2)
    }

  def flatMap[B](f: A => State[S,B]) : State[S,B] =
    State{(s:S) => 
      val (a, s2) = self.run(s)
      f(a).run(s2)
    }

  def map2[B,C](s: State[S, B])(f: (A, B) => C) : State[S,C] =
    State{(ss:S) =>
      val (a, s2) = self.run(ss)
      val (b, s3) = s.run(ss)
      (f(a, b), s3)
    }
}

// @see [companion object in Scala](https://docs.scala-lang.org/overviews/scala-book/companion-objects.html)
object State {

  def unit[S,A](a: A) : State[S,A] = State((s:S) => (a, s))

}


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

}




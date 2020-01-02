package gettingstarted

// When a function literal is defined, what is actually being defined in Scala
// is an object with a method `apply`.
// Scala has a special rule for methods with `apply` and that they can be
// invoked!
//
object MyFunctionLiterals {

  val add : Int => Int => Int = x => y => x + y

  // equivalent to `add`
  val + = new Function2[Int, Int, Int] {
    def apply(a: Int, b : Int) = a + b
  }

  def main(args: Array[String]) = {
    println(assert((+)(1, 2) == 3), "True")
    println(assert(add(1)(2) == 3), "True")
    println(assert((+).apply(1, 2) == 3), "True")
  }
}


package object parallel {

  // Monoids represent the idea of combining 2 things of the same type.
  // In simple terms, you can imagine the concatenation operator (++) stitching
  // either lists or strings together.
  trait Monoid[A] {
  
    def mzero : A
    def mapply(a: A, b: A) : A
  
  }
  // defined what it means for combine strings
  implicit val monoidString = new Monoid[String] {
    def mzero = ""
    def mapply(a: String, b: String) = a + b
  }
  
  implicit val functorList : Functor[List] =
    new Functor[List] {
      def map[A, B](fa: List[A])(f: A => B) : List[B] = fa.map(f)
    }

  // syntax sugar
  implicit class MonoidOps[A : Monoid](lhs: A) { 
    def |+|(rhs: A) = Monoid[A].mapply(lhs, rhs)
  }
 
  // singleton object which returns the implicit value
  object Monoid {
    def apply[A](implicit m : Monoid[A]) : Monoid[A] = m
  }

}


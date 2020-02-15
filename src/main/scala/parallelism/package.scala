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

  // Foldable is a behavior where i wish to represent the notion of folding a
  // structure with the end-goal of collapsing the entire structure to a single
  // value. However, i would need to extend from the Functor typeclass
  // (typeclass == behaviors) as it provides the basic notion of traversing a
  // structure (that is what "map" is for).
  trait Foldable[F[_]] extends Functor[F] {
    def fold[A](fa: F[A])(z: A)(f : (A,A) => A) : A
  }

  object Foldable {
    def apply[F[_]](implicit f : Foldable[F]) : Foldable[F] = f
  } 

  object FoldableInstances {

    // here is where i define what it means to fold a List of As
    // but take note that `Foldable` represents a structure.
    implicit val FL : Foldable[List] = new Foldable[List] {
      def fold[A](fa: List[A])(z:A)(f : (A, A) => A) : A = fa.fold(z)(f)
      def map[A,B](fa: List[A])(f: A => B) : List[B] = fa.map(f)
    }

  }

}


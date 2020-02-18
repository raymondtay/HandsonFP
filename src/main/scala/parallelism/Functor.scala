package parallel 

// Functors represent the idea of mapping a function over a structure.
// Its easier to imagine how functors work if you think about mapping a
// function over a list of elements.
//
trait Functor[F[_]] { self =>

  def map[A,B](fa: F[A])(f: A => B) : F[B]

  final def fmap[A, B](fa: F[A])(f: A => B): F[B] = map(fa)(f)

}

// singleton object which returns the implicit value
object Functor {
  def apply[F[_]](implicit F : Functor[F]) = F
}


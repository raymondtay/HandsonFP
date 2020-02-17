package parallel

import scala.util._
import scala.concurrent._
import scala.concurrent.duration._
import scala.language.postfixOps

/**
 * The functional Map Reduce algorithm
 * as rendered by Jimmy Lin (University of Maryland) paper on using Monoids
 * (Monoidify! Monoids as a Design Principle for Efficient MapReduce Algorithms)[http://arxiv.org/abs/1304.7544]
 *
 */
trait FoldMap {

  // finally collects and collapses the result.
  def foldMap[A,B : Monoid](xs: List[A])(f: A => B) : B = 
    xs.map(f).foldLeft(Monoid[B].mzero)(_ |+| _)

  def foldMapF[A,B : Monoid, F[_] : Functor : Foldable](xs: F[A])(f: A => B) : B = {
    val fb = Functor[F].map(xs)(f)
    Foldable[F].fold(fb)(Monoid[B].mzero)(_ |+| _)
  }

  // scala.concurrent.Future implementation 
  def foldMapPar[A,B: Monoid](xs: List[A])(f: A => B)(implicit ec : ExecutionContext) : Future[B] = {
    val numCores = Runtime.getRuntime.availableProcessors

    val groupSize = (1.0 * xs.size / numCores).ceil.toInt

    val groups = xs.grouped(groupSize) // create 1 group for each CPU
    val futures : Iterator[Future[B]] = // for each group, we fold the result
      groups.map(group => Future(foldMap(group)(f)))

    // now we have a list of futures, we collapse each value to a single value
    Future.sequence(futures).map{ iterable =>
      iterable.foldLeft(Monoid[B].mzero)(_ |+| _)
    }
  }
}

object MapReduce extends App with FoldMap {

  import FoldableInstances._

  // I have only defined how to combine values of the String type,
  // so it follows the demonstration is as follows.
  println(foldMap(List("1", "2", "3", "4", "5"))(_ + "!"))

  // Question: I have defined how to handle values of the element in a List, or
  // more generally i have defined how to handle values of the elements in a
  // structure (You should be convinced that List ∈ Structure) ; next question
  // to ask is whether we can generalize it to include the "Structure" in which
  // we apply to ?
  // Answer : Yes.

  println(foldMapF(List("1", "2", "3", "4", "5"))(_ + "!"))


  implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global


  println(Await.result(foldMapPar(List("1", "2", "3", "4", "5"))(_ + "!"), 1 second))

  val computation = foldMapPar(List("1", "2", "3", "4", "5"))(_ + "!")
  computation onComplete { 
    case Success(v) => println(s"Result => ${v}")
    case Failure(e) => e.printStackTrace
  }

}

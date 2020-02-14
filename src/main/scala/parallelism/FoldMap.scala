package parallel

/**
 * The functional Map Reduce algorithm
 * as rendered by Jimmy Lin (University of Maryland) paper on using Monoids
 * (Monoidify! Monoids as a Design Principle for Efficient MapReduce Algorithms)[http://arxiv.org/abs/1304.7544]
 *
 */
trait FoldMap {

  // Traverses the data structure and for each element, applies the function
  // finally collects and collapses the result.
  def foldMap[A,B : Monoid](xs: List[A])(f: A => B) : B = 
    xs.map(f).foldLeft(Monoid[B].mzero)(_ |+| _)

}


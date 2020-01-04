package handlingerrors

import scala.util._

// @see [[DivideBy.scala]] for canonical Scala error handling.
//
object MyCustomErrorHandling {

  def divBy(n: Int)(denoms: List[Int]) : Either[DivByError[Int],List[Int]] = {
    def go(dnoms: List[Int], accum: List[Int]) : Either[DivByError[Int],List[Int]] =
      dnoms match {
        case     Nil => Right( accum )
        case (0 ::_) => Left(DivBy0)
        case (8 ::_) => Left(ForbiddenDenominator(8))
        case (28::_) => Left(ForbiddenDenominator(28))
        case (d::ds) if (math.floorMod(n,d) == 0) => go(ds, math.floorDiv(n,d) :: accum)
        case (d::ds) => go(ds, accum)
      }
    if (denoms.isEmpty) Right(Nil) else go(denoms, List())
  }

  def main(args: Array[String]) : Unit = {

    println(divBy(50)(List(1,2,5,10)))
    println(divBy(50)(List(1,2,5,8,10)))
    println(divBy(50)(List(1,2,0,5,8,10)))
  }

}


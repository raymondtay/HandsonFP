package handlingerrors

import scala.util._

// Typical error handling procedures in Scala.
//
object MyErrorHandling {

  // No exception handling.
  def divBy(n : Int) = 
     List(1, 2, 5, 8, 10).map((denom: Int) => math.floorDiv(n,denom))

  // Exception handling with converters.
  def divBy2(n : Int) = 
     List(1, 2, 5, 8, 0, 10).map((denom: Int) => Try{math.floorDiv(n,denom)}.toEither)

  // Exception handling with converters.
  def divBy3(n : Int) = 
     List(1, 2, 0, 5, 8, 10).map((denom: Int) => Try{math.floorDiv(n,denom)}.toOption)

  def main(args: Array[String]) : Unit = {

    println(divBy(50))
    println(divBy2(50))
    println(divBy3(50))

  }

}


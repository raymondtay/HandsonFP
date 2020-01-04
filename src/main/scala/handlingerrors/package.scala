package object handlingerrors {

  sealed trait DivByError[+A]
  case object DivBy0 extends DivByError[Nothing]
  case class ForbiddenDenominator[A](value: A) extends DivByError[A]

}



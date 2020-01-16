package gettingstarted

import org.scalacheck._
import org.scalacheck.Gen._
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll


object MyHoF extends Properties("HoF") {

  implicit val good1 : Gen[(Array[Double], Double)] =
    for {
      xs <- Gen.containerOf[Array,Double](Gen.oneOf(1.2,3.2,.25))
      x  <- Gen.oneOf(1.2, 3.2, .25)
    } yield (xs, x)

  property("findFirst - Strings") =
    forAll { (t: (Array[String], String)) => 
      val result = MyTransformation.findFirst(t._1, t._2)
      (result == -1 || result >= 0)
    }

  property("findFirst - Ints") =
    forAll(good1) { (t: (Array[Double], Double)) => 
      val result = MyTransformation.findFirst[Double](t._1, t._2)
      (result == -1 || result >= 0)
    }

  property("isSorted - Ints") =
    forAll { (xs: Array[Int]) => 
      val result = MyTransformation.isSorted[Int](xs, (_ <= _ ))
      (result == false || result == true)
    }

}


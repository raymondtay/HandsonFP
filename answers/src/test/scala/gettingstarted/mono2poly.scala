package gettingstarted

import org.scalacheck._
import org.scalacheck.Gen._
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

trait DataGenerators {

  implicit val good1 : Gen[(Array[Int], Int)] =
    for {
      xs <- Gen.containerOf[Array,Int](Gen.oneOf(1,3,5))
      x  <- Gen.oneOf(1, 3, 5)
    } yield (xs, x)

  implicit val good2 : Gen[(Array[Double], Double)] =
    for {
      xs <- Gen.containerOf[Array,Double](Gen.oneOf(1.2,3.2,.25))
      x  <- Gen.oneOf(1.2, 3.2, .25)
    } yield (xs, x)

}

object MyTransformationSpecification extends Properties("MyTransformation") with DataGenerators {

  property("isSorted for Strings") =
    forAll { (t: (Array[String], String)) => 
      val result = MyTransformation.findFirst(t._1, t._2)
      (result == -1 || result >= 0)
    }

  property("isSorted for Ints") =
    forAll(good1) { (t: (Array[Int], Int)) => 
      val result = MyTransformation.findFirst[Int](t._1, t._2)
      (result == -1 || result >= 0)
    }

  property("isSorted for Double") =
    forAll(good2) { (t: (Array[Double], Double)) => 
      val result = MyTransformation.findFirst[Double](t._1, t._2)
      (result == -1 || result >= 0)
    }

}


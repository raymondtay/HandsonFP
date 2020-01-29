package datastructures

import org.scalacheck._
import org.scalacheck.Gen._
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

import scala.collection.immutable.ArraySeq

object MyList extends Properties("List") {

  implicit val good1 : Gen[Array[Int]] =
    for {
      xs <- Gen.containerOf[Array,Int](Gen.oneOf(1,2,3,4,5))
    } yield xs

  property("`sum` of ints") =
    forAll { (t: (Array[Int])) => 
      val result = List.sum(List(ArraySeq.unsafeWrapArray(t):_*))
      result == scala.collection.immutable.List(ArraySeq.unsafeWrapArray(t):_*).foldLeft(0)(_ + _)
    }

  property("`product` of ints") =
    forAll { (t: (Array[Int])) => 
      val result = List.product(List(ArraySeq.unsafeWrapArray(t):_*))
      result == scala.collection.immutable.List(ArraySeq.unsafeWrapArray(t):_*).foldLeft(1)(_ * _)
    }

  property("`append` left-/right-identity ") =
    forAll { (l: (Array[Int])) => 
      val result1 = List.append(List(ArraySeq.unsafeWrapArray(l):_*), EmptyList)
      val result2 = List.append(EmptyList, List(ArraySeq.unsafeWrapArray(l):_*))
      result1 == result2
    }

  property("`append` general scenarios ") =
    forAll { (l: (Array[Int], Array[Int])) => 
      val result1 = List.append(List(ArraySeq.unsafeWrapArray(l._1):_*), List(ArraySeq.unsafeWrapArray(l._2):_*))
      val result2 = List.reverse(result1) 
      result1 == List.reverse(result2)
    }

}


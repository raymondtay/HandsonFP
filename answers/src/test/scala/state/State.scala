package state

import org.scalacheck._
import org.scalacheck.Gen._
import Arbitrary.arbitrary
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import datastructures.{ List => _, _ }
import scala.collection.immutable.{List, ArraySeq}

object StateSpec extends Properties("State") {

  val one : State[Int, Int] = State.unit(1)
  val addOne : Int => Int = ( _ + 1)

  property("`unit` will always return its pre-defined result") =
    forAll { (input:Int) => 
      (one.run(input)._1 == 1)
    }

  property("`map` will always apply its function") =
    forAll { (input: Int) => 
      (one.map(addOne).run(input)._1 == 2)
    }

  property("`set` will always set its state w/o altering its result") =
    forAll { (input: Int) => 
      one.set(42).run(input)._2 == 42
    }

  property("`modify` will always replace its state, discarding its result.") =
    forAll { (input: Int) => 
      one.modify[Int](s => s+2).run(input)._2 == (input + 2)
    }

  property("`flatMap` will always chain its state, optionally returning a computation on its results") =
    forAll { (input: Int) => 
      one.flatMap(_a => one.flatMap(_b => State.unit(_a + _b))).run(input)._1 == 2
    }

  property("`map2` will combine two state functions via a binary operation") =
    forAll { (input: Int) =>
      one.map2(one)(_ * _).run(input)._1 == 1
    }

  property("`map2` will combine two state functions via a binary operation") =
    forAll { (input: Int) =>
      one.map2(one)(_ + _).run(input)._1 == 2
    }

}


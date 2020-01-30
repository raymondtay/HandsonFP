package datastructures

import org.scalacheck._
import org.scalacheck.Gen._
import Arbitrary.arbitrary
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import datastructures.{ List => _, _ }
import scala.collection.immutable.{List, ArraySeq}

object TreeSpec extends Properties("Tree") {

  implicit def arbTree[T](implicit a: Arbitrary[T]): Arbitrary[Tree[T]] = Arbitrary {
    val genLeaf = for(e <- Arbitrary.arbitrary[T]) yield Leaf(e)
  
    def genBranch(sz: Int): Gen[Tree[T]] = for {
      n <- Gen.choose(sz/3, sz/2)
      c <- Gen.listOfN(n, sizedTree(sz/2))
    } yield buildTree(c)
 
    def buildTree[T](ts: List[Tree[T]]) : Tree[T] = {
      def unfold(xs : List[Tree[T]], ts: Tree[T]) : Tree[T] =
        xs match {
          case Nil => ts
          case h :: t if h.isInstanceOf[Leaf[T]] => unfold(t, Branch(h, ts))
          case h :: t => unfold(t, Branch(h, ts))
        }
      unfold(ts, Leaf(0.asInstanceOf[T]))
    }

    def sizedTree(sz: Int) =
      if(sz <= 0) genLeaf
      else Gen.frequency((1, genLeaf), (3, genBranch(sz)))
  
    Gen.sized(sz => sizedTree(sz))
  }

  property("size of trees are at least zero") =
    forAll { (t: Tree[Int]) => 
      MyTree.size(t) >= 0
    }

  property("maximum of trees") =
    forAll { (t: Tree[Int]) => 
      MyTree.maximum(t) == MyTree.maximum(t)
    }

  property("depth of trees") =
    forAll { (t: Tree[Int]) => 
      MyTree.depth(t) >= 0
    }

  property("`map` of trees") =
    forAll { (t: Tree[Int]) => 
      MyTree.map(t)((e: Int) => s"${e}") == MyTree.map(t)((e: Int) => s"${e}")
    }

}


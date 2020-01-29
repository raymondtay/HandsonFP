package datastructures

sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](l: Tree[A], r: Tree[A]) extends Tree[A]

object MyTree {

  // Exercise 1: implement a function `size` that counts the number of nodes
  // (leaves and branches) in a tree.
  def size[A](tree: Tree[A]) : Int = {
    def go(ts: Tree[A], acc: Int) : Int = ts match {
      case Leaf(_) => acc
      case Branch(l, r) => size(l) + size(r) + 1
    }
    go(tree, 0)
  }

  // Exercise 2: implement a function `maximum` which returns the maximum
  // element in a Tree[Int]
  def maximum(tree: Tree[Int]) : Int = tree match {
    case Leaf(x) => x
    case Branch(l, r) => maximum(l) max maximum(r)
  }

  // Exercise 3: implement a function `depth` that returns the maximum path
  // length from the root of a tree to any leaf.
  def depth[A](tree: Tree[A]) : Int = {
    def go(ts: Tree[A], d: Int) : Int = ts match {
      case Leaf(_) => d + 1
      case Branch(l, r) => depth(l) max depth(r)
    }
    go(tree, 0)
  }

  // Exercise 4: implement a function `map` that transform each element of the
  // tree
  def map[A,B](tree: Tree[A])(f: A => B) : Tree[B] = tree match {
    case Leaf(a) => Leaf(f(a))
    case Branch(l , r) => Branch(map(l)(f), map(r)(f))
  }

  def main(args: Array[String]) : Unit = {

  }

}


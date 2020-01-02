package datastructures

sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](l: Tree[A], r: Tree[A]) extends Tree[A]

object MyTree {

  // Exercise 1: implement a function `size` that counts the number of nodes
  // (leaves and branches) in a tree.
  def size[A](tree: Tree[A]) : Int = ???

  // Exercise 2: implement a function `maximum` which returns the maximum
  // element in a Tree[Int]
  def maximum[A](tree: Tree[A]) : Int = ???

  // Exercise 3: implement a function `depth` that returns the maximum path
  // length from the root of a tree to any leaf.
  def depth[A](tree: Tree[A]) : Int = ???

  // Exercise 4: implement a function `map` that transform each element of the
  // tree
  def map[A,B](tree: Tree[A])(f: A => B) : Tree[B] = ???

  def main(args: Array[String]) : Unit = {

  }

}


package parallel

/**
 * In the scala 2.13 compiler, the parallel collections are moved to another
 * module aka (parallel collections)[https://github.com/scala/scala-parallel-collections]
 * and you have to import `import scala.collection.parallel.CollectionConverters._` in order to make ".par"
 * syntax sugar visible (Recall that ".par" is part of earlier Scala compiler
 * releases)
 */

object MapReduceScala213 extends App {

  // Leveraging the parallel collections in Scala 2.13.x compilers, we can see
  // and contrast the different approaches to providing concurrency to our
  // systems.
  import scala.collection.parallel.CollectionConverters._

  println(List("1", "2", "3", "4", "5").par.map(_ + "!").reduce(_ + _))

}


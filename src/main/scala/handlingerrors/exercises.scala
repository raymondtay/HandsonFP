package handlingerrors

case class Person(name: Name, age: Age)
case class Name(value: String)
case class Age(value: Int)

// Exercise : implement the following functionalities using the functions you
// wrote. The exercises should directly use your APIs (if you have not done
// them, you should go back and make sure you do them).
//
object EitherTeaOrMe {

  def mkName(name : String) : Either[String, Name] = ???

  def mkAge(age: Int) : Either[String, Age] = ???

  def mkPerson(name: String, age: Int) : Either[String, Person] = ???

  def main(args: Array[String]) : Unit = {

    val yourAge : Int = 20 // enter your age.

    println(mkPerson("your name", yourAge))

  }

}


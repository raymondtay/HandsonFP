package datastructures.json

// Now that you are comfortable with the List and Tree ADT, you might wish to
// try something else. Let's start with attempting to start your very own JSON
// library.
//
// Let's start by encoding our Json structures.
//
sealed trait Json
final case class JsObject(get: Map[String,Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
case object JsNull extends Json

// This represents a generic interface to convert any "allowable" value to Json
trait JsonWriter[A] {
  def write(value: A) : Json
}

trait JsonReader[A] {
  def read(value: Json) : A
}

// Let's start writing some instances of the JsonWriter to render Json, in
// Scala of course.
//

object JsonWriterInstances {

  implicit val stringWriter : JsonWriter[String] = 
    new JsonWriter[String] {
      def write(v: String) : Json = JsString(v)
    }

  implicit val doubleWriter : JsonWriter[Double] = 
    new JsonWriter[Double] {
      def write(v: Double) : Json = JsNumber(v)
    }

  implicit val nullWriter : JsonWriter[Null] = 
    new JsonWriter[Null] {
      def write(v: Null) : Json = JsNull
    }

  implicit val personWriter : JsonWriter[Person] = 
    new JsonWriter[Person] {
      def write(v: Person) : Json = 
        JsObject(Map(
          "name" -> JsString(v.name),
          "email" -> JsString(v.email)))
    }

}

// Let's define some reader instances
object JsonReaderInstances {
  
  implicit val stringReader : JsonReader[String] = 
    new JsonReader[String] {
      def read(v: Json) : String = v match {
        case JsString(x) => x
        case _ => throw new Exception("Not a string")
      }
    }

  implicit val doubleReader : JsonReader[Double] = 
    new JsonReader[Double] {
      def read(v: Json) : Double = v match {
        case JsNumber(v) => v
        case _ => throw new Exception("Not a number")
      }
    }

  implicit val nullReader : JsonReader[Null] = 
    new JsonReader[Null] {
      def read(v: Json) = v match {
        case JsNull => null
        case _ => throw new Exception("Not a null")
      }
    }

  implicit val personReader : JsonReader[Person] = 
    new JsonReader[Person] {
      def read(v: Json) : Person = 
        v match {
          case JsObject(m) => Person(stringReader.read(m("name")), stringReader.read(m("email")))
          case _ => throw new Exception("Not an object")
        }
    }

}


// Encoding of a "Person"
case class Person(name: String, email : String) 

object MyJson {
  import JsonWriterInstances._ // let's bring in the implicit values.
  import JsonReaderInstances._ // let's bring in the implicit values.

  def toJson[A](obj: A)(implicit writer : JsonWriter[A]) = 
    writer.write(obj)

  def fromJson[A](json: Json)(implicit reader : JsonReader[A]) = 
    reader.read(json)

  // Let's see how we can render the person in Json
  def main(args: Array[String]) : Unit = {
 
    val p = Person("raymond", "ray@abc.com")

    println(fromJson[Person](toJson(p))) // Scala compiler discovers the right implicit value and applies it.

  }

}


package datastructures.json

// syntax sugar can be so sweet 
package object syntax {

  implicit class JsonWriterOps[A](value : A) {
    def toJson(implicit writer : JsonWriter[A]) = 
      writer.write(value)
  }

  implicit class JsonReaderOps(value : Json) {
    def fromJson[A](implicit reader : JsonReader[A]) = 
      reader.read(value)
  }

}


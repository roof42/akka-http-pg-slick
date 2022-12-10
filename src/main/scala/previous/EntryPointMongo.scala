import org.mongodb.scala._
import org.mongodb.scala.model._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model.Updates.{combine, set}
import org.mongodb.scala.model._

import scala.collection.JavaConverters._
import org.mongodb.scala.result.{InsertOneResult, UpdateResult}
import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import org.mongodb.scala.result.DeleteResult

class Person(val firstName: String, val lastName: String)

object EntryPointMongo { // extends App {
  val mongoClient: MongoClient = MongoClient()
  val db: MongoDatabase = mongoClient.getDatabase("my-database")
  val cl: MongoCollection[Document] = db.getCollection("my-collection")

  val doc: Document = Document(
    "_id" -> 5,
    "name" -> "Redis",
    "type" -> "database",
    "count" -> 1,
    "info" -> Document("x" -> 303, "y" -> 302)
  )

  val maybeDoc: Future[InsertOneResult] = cl.insertOne(doc).toFuture()
  maybeDoc.onComplete {
    case Success(value)     => println("Inserted")
    case Failure(exception) => println("Fail to insert")
  }

  val maybeTarget: Future[Document] =
    cl.find(equal("_id", 0)).first().toFuture()
  maybeTarget.onComplete {
    case Success(value)     => println(s"Result ${value.toJson}")
    case Failure(exception) => println("Couldn't found a document")
  }

  val person = new Person("Twin", "Panitsombat")
  val maybe: Future[UpdateResult] =
    cl.updateOne(
      equal("_id", 2),
      combine(
        set("info.x", person.firstName),
        set("info.y", person.lastName)
      )
    ).toFuture()
  maybeDoc.onComplete {
    case Success(value)     => println("Update completed")
    case Failure(exception) => println("Fail to update")
  }

  val mayBeDeleted: Future[DeleteResult] =
    cl.deleteOne(equal("_id", 1)).toFuture()
  mayBeDeleted.onComplete {
    case Success(value)     => println("Delete successfully")
    case Failure(exception) => println("Unable to delete")
  }

}

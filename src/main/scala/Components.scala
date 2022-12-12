import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._

import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

import akka.http.scaladsl.server.Route

trait MessageRouting extends JsonMarshallerComponent with Repository {

  val route: Route = cors() {
    pathPrefix("message") {
      ping ~ aMessage ~ allMessages ~ createNewMessage
    }
  }

  val ping: Route = path("ping") {
    get { complete("OK") }
  }

  val allMessages: Route = pathEnd {
    get {
      val mayBeAllMessages = getAllMessages()
      onSuccess(mayBeAllMessages) {
        case messages: Seq[Message] => complete(messages.toList)
        case _                      => complete(StatusCodes.NotFound)
      }
    }
  }

  val aMessage: Route = path(LongNumber) { targetID =>
    get {
      val mayBeAMessage = getAMessage(targetID)
      onSuccess(mayBeAMessage) {
        case messages: Seq[Message] => complete(messages.toSet.head)
        case _                      => complete(StatusCodes.NotFound)
      }
    }
  }

  val createNewMessage: Route = pathEnd {
    post {
      entity(as[Message]) { newMessage =>
        val mayBeResult = createNewMessage(newMessage)
        onSuccess(mayBeResult) {
          case id: Option[Int] => complete(id.get.toString)
          case _               => complete(StatusCodes.InternalServerError)
        }
      }
    }
  }
}

import spray.json.RootJsonFormat

trait JsonMarshallerComponent {
  implicit val messageFormat: RootJsonFormat[Message] = jsonFormat3(Message)
}

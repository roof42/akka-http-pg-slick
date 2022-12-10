import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._

import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

import akka.http.scaladsl.server.Route

trait MessageRouting extends JsonMarshallerComponent {
  val route: Route = pathPrefix("message") {
    createNewMessage
  }

  val createNewMessage: Route = pathEnd {
    post {
      entity(as[Message]) { newMessage =>
        println(newMessage)
        complete(StatusCodes.OK)
      }
    }
  }
}

import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

trait JsonMarshallerComponent {
  implicit val messageFormat: RootJsonFormat[Message] = jsonFormat3(Message)
}

case class Message(id: Long, sender: String, message: String)

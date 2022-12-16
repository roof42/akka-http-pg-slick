import akka.http.scaladsl.Http
import scala.concurrent.Future

import scala.util.{Failure, Success}
import foundation.ActorContextComponent
import message.Routing

object AkkaHttpSimple extends App with ActorContextComponent with Routing {
  val bindingFuture: Future[Http.ServerBinding] =
    Http().newServerAt("0.0.0.0", 8080).bind(route)
  bindingFuture.onComplete {
    case Success(_) => println(s"<-------Started------>")
    case Failure(e) => println("Failed to start ... ", e)
  }
}

import akka.http.scaladsl.Http
import scala.io.StdIn
import scala.concurrent.Future

import scala.util.{Failure, Success}

object AkkaHttpSimple
    extends App
    with ActorContextComponent
    with MessageRouting {

  val bindingFuture: Future[Http.ServerBinding] =
    Http().newServerAt("0.0.0.0", 8080).bind(route)
  bindingFuture.onComplete {
    case Success(_) => println(s"<-------Started------>")
    case Failure(e) => println("Failed to start ... ", e)
  }
}

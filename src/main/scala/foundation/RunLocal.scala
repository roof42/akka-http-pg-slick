package foundation
import akka.http.scaladsl.Http
import scala.io.StdIn
import scala.concurrent.Future
import message.Routing

object RunLocal extends App with ActorContextComponent with Routing {
  val bindingFuture: Future[Http.ServerBinding] =
    Http().newServerAt("0.0.0.0", 8080).bind(route)
  StdIn.readLine
  bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
}

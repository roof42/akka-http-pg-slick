import akka.http.scaladsl.Http
import scala.io.StdIn
import scala.concurrent.Future

object BasicHttp extends App with ActorContextComponent with MessageRouting {
  val bindingFuture: Future[Http.ServerBinding] =
    Http().newServerAt("localhost", 8080).bind(route)
  StdIn.readLine()
  bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
}

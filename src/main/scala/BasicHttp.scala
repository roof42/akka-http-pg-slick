import akka.http.scaladsl.Http
import scala.io.StdIn

object AkkaHttpSimple extends App with Context with MessageRouting {
  val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)
  StdIn.readLine()
  bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
}

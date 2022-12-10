import actor.Context
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import scala.io.StdIn
import communication.routing.MessageRouting

object AkkaHttpSimple extends App with Context with MessageRouting {
  val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)
  StdIn.readLine()
  bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
}

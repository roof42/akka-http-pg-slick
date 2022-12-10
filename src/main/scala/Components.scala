import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import scala.io.StdIn

trait MessageRouting {
  val route = path("hello") { complete("OK") }
}

trait Context {
  implicit val system = ActorSystem(Behaviors.empty, "my-system")
  implicit val executionContext = system.executionContext
}

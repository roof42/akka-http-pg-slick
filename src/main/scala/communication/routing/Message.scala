package communication.routing

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import scala.io.StdIn

trait MessageRouting {
  val route = path("hello") { complete("OK") }
}

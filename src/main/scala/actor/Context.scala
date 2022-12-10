package actor

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors

trait Context {
  implicit val system = ActorSystem(Behaviors.empty, "my-system")
  implicit val executionContext = system.executionContext
}

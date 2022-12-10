import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import scala.concurrent.ExecutionContextExecutor
trait ActorContextComponent {
  implicit val system: ActorSystem[Any] =
    ActorSystem(Behaviors.empty, "my-system")
  implicit val executionContext: ExecutionContextExecutor =
    system.executionContext
}

import scala.concurrent.Future
import slick.jdbc.PostgresProfile.api._

trait Repository extends SlickComponent {
  def getAllMessages(): Future[Seq[Message]] =
    db.run(messages.result)
  def getAMessage(targetId: Long): Future[Seq[Message]] = {
    db.run(messages.filter(_.id === targetId).result)
  }
}

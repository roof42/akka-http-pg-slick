import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

// Main class for 'sbt initData'
object InitialDatabase extends App with SlickComponent {
  private def exec[T](program: DBIO[T]): T =
    Await.result(db.run(program), 2.second)

  println("\nDrop database if exist")
  exec(messages.schema.dropIfExists)

  println("\nCreating database table")
  exec(messages.schema.createIfNotExists)

  println("\nInserting test data")
  exec(
    messages ++= Seq(
      Message("Dave", "Hello, HAL. Do you read me, HAL?"),
      Message("HAL", "Affirmative, Dave. I read you."),
      Message("Dave", "Open the pod bay doors, HAL."),
      Message("HAL", "I'm sorry, Dave. I'm afraid I can't do that.")
    )
  )
}

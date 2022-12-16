package message
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

trait SlickComponent {
  val db: PostgresProfile.backend.Database = Database.forConfig("basicSlick")
}

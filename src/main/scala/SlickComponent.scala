import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

trait SlickComponent {
  lazy val messages: TableQuery[MessageTable] = TableQuery[MessageTable]
  val db: PostgresProfile.backend.Database = Database.forConfig("basicSlick")
}

package message
import slick.sql.SqlProfile.ColumnOption.SqlType
import slick.jdbc.PostgresProfile.api._
final class MessageTable(tag: Tag) extends Table[Message](tag, "message") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def sender = column[String]("sender")
  def content = column[String]("content")
  override def * = (sender, content, id) <> (Message.tupled, Message.unapply)
}

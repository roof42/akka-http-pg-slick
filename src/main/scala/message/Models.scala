package message
final case class Message(sender: String, content: String, id: Long = 0L)

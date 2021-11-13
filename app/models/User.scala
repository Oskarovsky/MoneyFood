package models

case class User(userId: Long, username: String, email: String) {
  override def equals(that: Any): Boolean = ???
}

object User {

  val userList = Seq(
    User(1, "Dave", "dave@gmail.com"),
    User(2, "Maurisio", "maurisio@gmail.com"),
    User(3, "Mika", "mika1234@gmail.com")
  )

  def getUserById(userId: Long): Seq[User] = {
    userList.filter(a => a.userId.equals(userId))
  }
}

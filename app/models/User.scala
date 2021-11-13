package models

case class User(userId: Long, username: String, email: String) {

  override def equals(that: Any): Boolean = ???
}

package models

case class Subscription(email: String, interval: Long) {
  override def equals(that: Any): Boolean = ???
}

object Subscription

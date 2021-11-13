package controllers

import models.Subscription
import play.api.libs.Files
import play.api.libs.json.{JsValue, Json, OWrites}
import play.api.mvc._

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global


@Singleton
class UserController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def subscribe: Action[JsValue] = Action(parse.tolerantJson) {
    request =>
      val reqData: JsValue = request.body
      val email = (reqData \ "email").as[String]
      val login = (reqData \ "login").as[String]
      Ok(s"Added $email to subscriber's list for user $login")
  }

  val parseAsSubscription: BodyParser[Subscription] = parse.using {
    request =>
      parse.json.map {
        body =>
          val email: String = (body \ "email").as[String]
          val fromDate:Long = (body \ "fromDate").as[Long]
          Subscription(email, fromDate)
      }
  }

  implicit val subWrites: OWrites[Subscription] = Json.writes[Subscription]

  def getSub: Action[Subscription] = Action(parseAsSubscription) {
    request =>
      val subscription: Subscription = request.body
      Ok(Json.toJson(subscription))
  }


  def createUser: Action[MultipartFormData[Files.TemporaryFile]] = Action(parse.multipartFormData) {
    request => {
      val formData = request.body.asFormUrlEncoded
      val email: String = formData("email").head
      val username: String = formData("username").head
      Ok(s"Successfully added user $username with email $email")
    }
  }

}

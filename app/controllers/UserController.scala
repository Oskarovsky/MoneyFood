package controllers

import models.{Product, ProductDto, Subscription, User}
import play.api.libs.Files
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsValue, Json, OWrites}
import play.api.mvc._

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global


@Singleton
class UserController @Inject()(val cc: ControllerComponents) extends AbstractController(cc) {

  implicit val productListJson = Json.format[User]

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

  /* API */
  def subscribe: Action[JsValue] = Action(parse.tolerantJson) {
    request =>
      val reqData: JsValue = request.body
      val email = (reqData \ "email").as[String]
      val login = (reqData \ "login").as[String]
      Ok(s"Added $email to subscriber's list for user $login")
  }

  def getUserById(userId: Long): Action[AnyContent] = Action {
    val foundUser = User.getUserById(userId).find(_.userId == userId)
    foundUser match {
      case Some(user) => Ok(Json.toJson(user))
      case None => NotFound
    }
  }

  def createUser: Action[MultipartFormData[Files.TemporaryFile]] = Action(parse.multipartFormData) {
    request => {
      val formData = request.body.asFormUrlEncoded
      val email: String = formData("email").head
      val username: String = formData("username").head
      Ok(s"Successfully added user $username with email $email")
    }
  }

  def getSub: Action[Subscription] = Action(parseAsSubscription) {
    request =>
      val subscription: Subscription = request.body
      Ok(Json.toJson(subscription))
  }

}

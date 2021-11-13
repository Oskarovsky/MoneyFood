package controllers

import play.api.libs.EventSource.Event.writeable
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.xml.Node

@Singleton
class ConfigController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def getConfig: Action[AnyContent] = Action {
    implicit request =>
      val xmlResponse: Node =
        <metadata>
          <application>MoneyFood</application>
          <version>0.1</version>
        </metadata>

      val jsonResponse = Json.obj("metadata" -> Json.arr(
        Json.obj("application" -> "MoneyFood"),
        Json.obj("version" -> "0.1"))
      )
      render {
        case Accepts.Xml() => Ok(xmlResponse)
        case Accepts.Json() & Accepts.JavaScript => Ok(jsonResponse)
      }
  }

}

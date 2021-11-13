package controllers

import models.Purchase
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import java.io.File
import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


@Singleton
class PurchaseController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def purchasesList: Action[AnyContent] = Action {
    val purchases = Purchase.getPurchasesList()
    if (purchases.isEmpty) {
      NoContent
    } else {
      Ok(views.html.purchase(purchases))
    }
  }

  def getPurchaseReport(fileName: String): Action[AnyContent] = Action.async {
    Future {
      Ok(s"New report edited on ")
      File
    }
  }

}

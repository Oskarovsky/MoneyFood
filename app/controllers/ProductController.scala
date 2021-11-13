package controllers

import models.{Product, ProductDto}
import play.api.libs.json.Json
import play.api.mvc._

import javax.inject.{Inject, Singleton}

@Singleton
class ProductController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  implicit val productListJson = Json.format[Product]

  implicit val productDtoJson = Json.format[ProductDto]

  val productList = Seq(
    Product(1, "Tomato", 10.10, "VEGETABLE"),
    Product(2, "Sandwich with ham", 5.10, "SANDWICHES"),
    Product(3, "Ice cream", 3.11, "SWEET"),
    Product(4, "Sandwich", 4.95, "SANDWICHES"),
    Product(5, "Pepper", 2.99, "SPICES"),
    Product(6, "Chicken", 13.29, "MEAT"),
  )

  /* VIEW */
  def productsList(name: Option[String], productType: Option[String]): Action[AnyContent] = Action {
    val products = Product.getProductsByNameOrType(name, productType)
    if (products.isEmpty) {
      NoContent
    } else {
      Ok(views.html.productList(products))
    }
  }

  def productInfo(productId: Int): Action[AnyContent] = Action {
    val product = Product.getProductById(productId)
    if (product.isEmpty) {
      NoContent
    } else {
      Ok(views.html.product(Product.getProductById(productId).head))
    }
  }

  /* API */

  def getProductsList(name: Option[String], productType: Option[String]): Action[AnyContent] = Action {
    val products = Product.getProductsByNameOrType(name, productType)
    if (products.isEmpty) {
      NotFound
    } else {
      Ok(Json.toJson(products))
    }
  }

  def getProductById(productId: Int): Action[AnyContent] = Action {
    val foundProduct = productList.find(_.id == productId)
    foundProduct match {
      case Some(product) => Ok(Json.toJson(product))
      case None => NotFound
    }
  }

  def getProductsByType(productType: String): Action[AnyContent] = Action {
    val foundProduct = Product.getProductsByType(productType)
    if (foundProduct.isEmpty) {
      NoContent
    } else {
      Ok(Json.toJson(foundProduct))
    }
  }


}

package models

case class Product(id: Int, name: String, price: Double, productType: String) {
  override def equals(that: Any): Boolean = ???
}

object Product {

  val productList = Seq(
    Product(1, "Tomato", 10.10, "VEGETABLE"),
    Product(2, "Sandwich with ham", 5.10, "SANDWICHES"),
    Product(3, "Ice cream", 3.11, "SWEET"),
    Product(4, "Sandwich", 4.95, "SANDWICHES"),
    Product(5, "Pepper", 2.99, "SPICES"),
    Product(6, "Salt", 1.99, "SPICES"),
    Product(7, "Chicken", 13.29, "MEAT"),
    Product(8, "Chicken Wings", 22.44, "MEAT"),
  )

  def getProductById(id: Int): Seq[Product] = {
    productList.filter(a => a.id.equals(id))
  }

  def getProductByName(name: String): Seq[Product] = {
    productList.filter(a => a.name.contains(name))
  }

  def getProductsByType(productType: String): Seq[Product] = {
    productList.filter(a => a.productType == productType)
  }

  def getProductsByNameOrType(name: Option[String], productType: Option[String]): Seq[Product] = {
    name match {
      case Some(_) =>
        if (productType.isDefined) {
          productList.filter(a => a.name == name.get || a.productType == productType.get)
        } else {
          getProductByName(name.get)
        }
      case None =>
        if (productType.isDefined) {
          productList.filter(a => a.productType == productType.get)
        } else {
          productList
        }
    }
  }
}
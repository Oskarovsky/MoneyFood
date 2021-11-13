package models

case class Item(id: Int, product: Product, amount: Double, price: Double) {
  override def equals(that: Any): Boolean = ???
}

object Item {

  val itemsList = Seq(
    Item(1, Product.getProductById(1).head, 1.22, Product.getProductById(1).head.price * 1.22),
    Item(2, Product.getProductById(2).head, 2, Product.getProductById(2).head.price * 2),
    Item(3, Product.getProductById(4).head, 1, Product.getProductById(4).head.price * 1),
    Item(4, Product.getProductById(6).head, 3.11, Product.getProductById(6).head.price * 3.11),
  )

  def getItemById(id: Int): Seq[Item] = {
    itemsList.filter(a => a.id.equals(id))
  }
}

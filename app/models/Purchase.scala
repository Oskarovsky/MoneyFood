package models

case class Purchase(id: Int, items: List[Item], shop: String) {
  override def equals(that: Any): Boolean = ???
}

object Purchase {

  val purchasesList = Seq(
    Purchase(1, List(Item.getItemById(1).head, Item.getItemById(2).head), "Lidl"),
    Purchase(2, List(Item.getItemById(3).head), "Zabka"),
    Purchase(3, List(Item.getItemById(4).head), "Biedronka")
  )

  def getPurchaseById(id: Int): Seq[Purchase] = {
    purchasesList.filter(a => a.id.equals(id))
  }

  def getPurchasesList(): Seq[Purchase] = {
    purchasesList
  }
}
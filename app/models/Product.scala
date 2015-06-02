package models

case class Product(
  productId: Option[ProductId] = None,
  name: String,
  width: Option[Int] = None,
  height: Option[Int] = None, 
  weight: Option[Int] = None,
  creationDate: Option[java.util.Date] = Some(new java.util.Date)
)

case class ProductId(val value: String)





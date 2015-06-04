package domain.model.product

import domain.model._
import domain.shared.Entity

case class Product(
  id: Option[ProductId] = None,
  name: String,
  description: String = "",
  publishedDate: Option[java.util.Date] = None,
  variants: Option[List[Variant]] = None
) extends Entity {
  def published : Boolean = publishedDate match {
    case Some(date) => true
    case _ => false
  }
}

case class Variant(
  id: Option[VariantId] = None,
  productId: Option[ProductId],
  sku: String = "",
  option: Option[String] = None,
  costInCent: Int = 0
) extends Entity



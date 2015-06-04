package domain.model.product

import domain.model._
import scala.concurrent.Future

trait ProductRepository {
  def findById(id: ProductId) : Future[Option[Product]]
  def findAll(offset:Int, limit:Int) : Future[(Int, List[Product])]
  def store(product:Product) : Future[ProductId]
  def update(product:Product) : Future[Int]
  def delete(id:ProductId) : Future[Int]
}

trait VariantRepository {
  def findById(id: VariantId) : Future[Option[Variant]]
  def findByProductId(productId: ProductId) : Future[(Int, List[Variant])]
  def store(variant:Variant) : Future[VariantId]
  def update(variant:Variant) : Future[Int]
  def delete(id:VariantId) : Future[Int]
}

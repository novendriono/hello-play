package persistence.product

import persistence._
import domain.model._
import domain.model.product._

import anorm._
import anorm.SqlParser._

import scala.concurrent.Future

import java.util.UUID


class AnormProductRepository extends ProductRepository {

  def variantParser = for {
    id <- get[UUID]("id")
    productId <- get[Option[UUID]]("product_id")
    sku <- str("sku")
    option <- get[Option[String]]("option")
    costInCent <- get[Int]("cost_in_cent")
  } yield(Variant(Some(id), productId, sku, option, costInCent))

  def parser = for {
    id <- get[UUID]("id")
    name <- str("name")
    description <- str("description")
    publishedDate <- get[Option[java.util.Date]]("published_date")
  } yield(Product(Some(id), name, description, publishedDate))

  def findById(id: ProductId) = Future[Option[Product]] {
    DB.withConnection { implicit connection =>
      SQL("select * from product where id = {id}")
        .on('id -> id.toString()).as(parser.singleOpt) match {
          case Some(product) => Some(product.copy(variants = 
            Some(SQL("select * from variant where product_id = {id}").on('id -> id.toString()).as(variantParser *))))
          case None => None
        }
    }
  }

  def findAll(offset:Int, limit:Int) = Future[(Int, List[Product])] {
    DB.withConnection { implicit connection =>
      (
        SQL("select count(1) from product")
          .as(scalar[Int].single),
        SQL("select * from product order by name limit {limit} offset {offset}")
          .on('limit -> limit, 'offset -> offset)
          .as(parser.*)
      )
    }
  }

  def store(product:Product) = Future[ProductId] {
    DB.withConnection { implicit connection =>
      val productId = uuid
      SQL("""insert into Product(id, name, description, published_date)
        values ({id}, {name}, {description}, {publishedDate})"""
      ).on(
        'id -> productId,
        'name -> product.name,
        'description -> product.description,
        'publishedDate -> product.publishedDate
      ).executeUpdate()

      product.variants match {
        case Some(variants) => variants.foreach(
          variant => SQL(
            "insert into Variant values({id}, {productId}, {sku}, {option}, {costInCent})"
          ).on(
            'id -> uuid, 
            'productId -> productId,
            'sku -> variant.sku,
            'option -> variant.option,
            'costInCent -> variant.costInCent
          ).executeUpdate())
        case None =>
      }
      productId
    }
  }

  def update(product:Product) = Future[Int] {
    DB.withConnection { implicit connection =>
      val updated = SQL("""update product set
        name={name},
        description={description},
        published_date={publishedDate}
        where id={id}"""
      ).on(
        'id -> product.id,
        'name -> product.name,
        'description -> product.description,
        'publishedDate -> product.publishedDate
      ).executeUpdate()
      SQL("delete from variant where product_id = {id}").on('id -> product.id).executeUpdate()
      product.variants match {
        case Some(variants) => variants.foreach(
          variant => SQL(
            "insert into Variant values({id}, {productId}, {sku}, {option}, {costInCent})"
          ).on(
            'id -> uuid, 
            'productId -> product.id,
            'sku -> variant.sku,
            'option -> variant.option,
            'costInCent -> variant.costInCent
          ).executeUpdate())
        case None =>
      }
      updated
    }
  }

  def delete(id:ProductId) : Future[Int] = Future[Int] {
    DB.withConnection { implicit connection =>
      SQL("delete from variant where product_id = {id}").on('id -> id).executeUpdate()
      SQL("delete from product where id = {id}").on('id -> id).executeUpdate()
    }
  }
}

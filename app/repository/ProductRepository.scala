package repository

import models.Product
import models.ProductId

import anorm._
import anorm.SqlParser._

import scala.concurrent.Future

trait ProductRepository {
  def findById(id: ProductId) : Future[Option[Product]]
  def findAll(offset:Int, limit:Int) : Future[(Int, List[Product])]
  def store(product:Product) : Future[ProductId]
  def update(product:Product) : Future[Int]
  def delete(id:ProductId) : Future[Int]
}

class AnormProductRepository extends ProductRepository {
  val countAll = SQL("select count(1) from product")
  val all = SQL("""
     SELECT *
        FROM Product
      ORDER BY name
      LIMIT {limit} OFFSET {offset}
  """)

  val byId = SQL("""
     SELECT *
        FROM Product
      WHERE id = {id}
  """)

  val parser = for {
    id <- str("id")
    name <- str("name")
    width <- get[Option[Int]]("width")
    height <- get[Option[Int]]("height")
    weight <- get[Option[Int]]("weight")
    creationDate <- get[java.util.Date]("creation_date")
  } yield (Product(Some(ProductId(id)), name, width, height, weight, Some(creationDate)))

  def findById(id: ProductId) = Future {
    DB.withConnection { implicit connection =>
      byId.on('id -> id.value).as(parser.singleOpt)
    }
  }

  def findAll(offset:Int, limit:Int) = Future[(Int, List[Product])] {
    DB.withConnection { implicit connection =>
      ( countAll.as(scalar[Int].single),
        all.on(
          'limit -> limit,
          'offset -> offset
        ).as(parser.*))
    }
  }

  def store(product: Product) = Future[ProductId] {
    DB.withConnection { implicit connection =>
      val productId = ProductId(uuid)
      SQL(
        """
        insert into Product(id, name, width, height, weight) 
        values ({id}, {name}, {width}, {height}, {weight})
        """
      ).on(
        'id -> productId.value,
        'name -> product.name,
        'width -> product.width,
        'height -> product.height,
        'weight -> product.weight
      ).executeUpdate()
      productId
    }
  }

  def update(product: Product) = Future[Int] {
    DB.withConnection { implicit connection =>
      SQL("""
        update Product set name={name}, width={width}, height={height}, weight={weight}
        where id = {id} """
      ).on(
        'id -> product.productId.getOrElse(ProductId("")).value,
        'name -> product.name,
        'width -> product.width,
        'height -> product.height,
        'weight -> product.weight
      ).executeUpdate()
    }
  }

  def delete(id: ProductId) = Future[Int] {
    DB.withConnection { implicit connection =>
      SQL("delete from Product where id = {id}").on('id -> id.value).executeUpdate()
    }
  }

}


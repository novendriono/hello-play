package models

import anorm._
import play.api.db.DB

import scala.concurrent.Future

object ProductRepository {
  val all = SQL("""
     SELECT *
        FROM Product
  """)

  val byId = SQL("""
     SELECT *
        FROM Product
      WHERE id = {id}
  """)

  def findById(id: ProductId) = Future {
    DB.withConnection { implicit connection =>
      byId.on('id -> id.value)().map(
        product => Product(
          ProductId(product[String]("id")),
          product[String]("name"),
          product[Option[Int]]("width"),
          product[Option[Int]]("height"),
          product[Option[Int]]("weight")
        )
      ).toList
    }
  }

  def findAll() = Future {
    DB.withConnection { implicit connection =>
      all().map(
        product => Product(
          ProductId(product[String]("id")),
          product[String]("name"),
          product[Option[Int]]("width"),
          product[Option[Int]]("height"),
          product[Option[Int]]("weight")
        )
      ).toList
    }
  }

}

case class Product(
  productId: ProductId = ProductId(""),
  name: String,
  width: Option[Int] = None,
  height: Option[Int] = None, 
  weight: Option[Int] = None)
case class ProductId(val value: String)

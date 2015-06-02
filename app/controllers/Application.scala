package controllers

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import models.Product
import models.ProductId


object Product extends Controller with Authentication {

  implicit val productIdReads : Reads[ProductId] = (JsPath).read[String].map(ProductId(_))

  implicit val productReads : Reads[Product] = (
    (JsPath \ "id").read[ProductId] and
    (JsPath \ "name").read[String] and
    (JsPath \ "width").readNullable[Int] and
    (JsPath \ "height").readNullable[Int] and
    (JsPath \ "weight").readNullable[Int] 
  )(Product.apply _)

  implicit val productWrites = new Writes[Product]  {
    def writes(product: Product) = Json.obj(
      "id" -> product.productId.value,
      "name" -> product.name,
      "width" -> product.width,
      "height" -> product.height,
      "weight" -> product.weight
    )
  }

  def list = Action { implicit request => 
    Ok(Json.toJson(Product.all()))
  }

  def add = Action(BodyParsers.parse.json) { implicit request =>
    val productResult = request.body.validate[Product]
    productResult.fold(
      errors => {
        BadRequest(Json.obj("status" -> "Error", "message" ->JsError.toFlatJson(errors)))
      },
      product => {
        Status(201)(Json.obj("status" -> "OK", "message" -> "saved"))
      }
    )
  }
}

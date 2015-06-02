package controllers

import play.api.mvc._
import play.api.libs.json._
import play.api.data.validation.ValidationError

import models.Product
import models.ProductId

import repository.AnormProductRepository

import scala.concurrent.Future

object ProductCtrl extends Controller {

  val productRepository = new AnormProductRepository

  def badRequest(errors: Seq[(JsPath, Seq[ValidationError])]) = BadRequest(Json.obj("status" -> "Error", "message" -> JsError.toFlatJson(errors)))

  implicit val productIdReads : Reads[ProductId] = (JsPath).read[String].map(ProductId(_))
  implicit val productIdWrite : Writes[ProductId] = Writes[ProductId](productId => JsString(productId.value))
  implicit val productFormat = Json.format[Product]

  def list(offset:Int, limit:Int)  = Action.async { implicit request => 
    productRepository.findAll(offset, limit) map {
      case (total: Int, products:List[Product]) => Ok(Json.toJson(Json.obj(
        "offset" -> offset,
        "limit" -> limit,
        "total" -> total,
        "result" -> products)))
      case  _ => NoContent
    }
  }

  def get(id: String) = Action.async { implicit request =>
    productRepository.findById(ProductId(id)) map {
      case Some(product:Product) => Ok(Json.toJson(product))
      case _ => NotFound
    }
  }

  def add = Action.async(BodyParsers.parse.json) { implicit request =>
    val productResult = request.body.validate[Product]
    productResult.fold(
      errors => {
        Future[Result]{BadRequest(Json.obj("status" -> "Error", "message" ->JsError.toFlatJson(errors)))}
      },
      product => {
        productRepository.store(product) map {
          case productId:ProductId => Created(Json.obj("status" -> "Created", "productId" -> productId.value))
          case _ => InternalServerError("Failed to add new product")
        }
      }
    )
  }

  def update(id: String) = Action.async(BodyParsers.parse.json) { implicit request =>
    val productResult = request.body.validate[Product]
    productResult.fold(
      errors=> {
        Future[Result]{badRequest(errors)}
      },
      product => {
        productRepository.update(product) map {
          case updated: Int if updated > 0 => Ok(Json.obj("status" -> s"Updated %s product".format(updated)))
          case _ => NotFound
        }
      }
    )
  }

  def delete(id: String) = Action.async { implicit request =>
    productRepository.delete(ProductId(id)) map {
      case deleted: Int if deleted > 0 => Ok(Json.obj("status" -> s"Deleted product : %s".format(id)))
      case _ => NotFound
    }
  }
}

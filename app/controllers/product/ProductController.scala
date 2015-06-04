package controllers.product

import controllers._
import play.api.mvc._
import play.api.libs.json._
import play.api.data.validation.ValidationError

import domain.model._
import domain.model.product.Product
import domain.model.product.Variant

import persistence.product.AnormProductRepository

import scala.concurrent.Future
import scala.util.Try

import java.util.UUID

object ProductController extends Controller {

  implicit val variantFmt = Json.format[Variant]
  implicit val productFmt = Json.format[Product]

  val productRepository = new AnormProductRepository
  
  def list(offset: Int, limit: Int) = Action.async { implicit request =>
    productRepository.findAll(offset, limit) map {
      case (total: Int, products: List[Product]) =>
        Ok(Json.toJson(Json.obj(
          "offset" -> offset,
          "limit" -> limit,
          "total" -> total,
          "result" -> products
        )))
      case _ => NoContent
    }
  }

  def get(id: String) = Action.async { implicit request =>
    Try(UUID.fromString(id)).toOption match {
      case Some(uuid) => productRepository.findById(uuid) map {
        case Some(product:Product) => Ok(Json.toJson(product))
        case _ => NotFound
      }
      case _ => Future{BadRequest}
    }
  }

  def add() = Action.async(BodyParsers.parse.json) { implicit request =>
    request.body.validate[Product].fold(
      errors => {
        Future[Result]{BadRequest}
      },
      product => {
        productRepository.store(product) map {
          case productId:UUID => Created(Json.obj("id" -> productId.toString))
          case _ => InternalServerError("Failed to add new product")
        }
      }
    )
  }

  def update(id: String) = Action.async(BodyParsers.parse.json) { implicit request =>
    request.body.validate[Product].fold(
      errors => {
        Future[Result]{BadRequest(JsError.toFlatJson(errors))}
      },
      product => {
        Try(UUID.fromString(id)).toOption match {
          case Some(uuid) => productRepository.update(product) map {
            case i: Int if i > 0 => Ok(Json.toJson(product))
            case _ => InternalServerError("Failed to update new product")
          }
          case _ => Future{BadRequest}
        }
      }
    )
  }

  def delete(id: String) = Action.async { implicit request =>
    Try(UUID.fromString(id)).toOption match {
      case Some(uuid) => productRepository.delete(uuid) map {
        case deleted: Int if deleted > 0 => Ok(Json.obj("status" -> s"Deleted product : %s".format(uuid)))
        case _ => NotFound
      }
      case _ => Future{BadRequest}
    }
  }
  
}

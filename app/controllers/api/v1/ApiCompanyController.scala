package controllers.api.v1

import javax.inject._

import akka.actor.ActorSystem
import models.Company
import models.daos.CompanyDAO
import play.api.Configuration
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Format, Json}
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext

import controllers.api.v1.responses.{SuccessResponse, ErrorResponse, EndpointResponse}


/**
  * Created by diogo on 19.10.16.
  *
  */

@Singleton
class ApiCompanyController @Inject()(configuration: Configuration, companyDAO: CompanyDAO)
                                    (implicit exec: ExecutionContext) extends Controller {

  //val config = configuration.getString("my.config").getOrElse("none")

  /* this whole block, or the next for shorter if reads and writes are the same
  implicit val companyWrites: Writes[Company] = (
    (JsPath \ "id").write[Integer] and
      (JsPath \ "name").write[String] and
      (JsPath \ "description").write[Option[String]] and
      (JsPath \ "logo").write[Option[String]]
    )(unlift(Company.unapply))

  implicit val companyReads: Reads[Company] = (
    (JsPath \ "id").read[Integer] and
      (JsPath \ "name").read[String] and
      (JsPath \ "description").read[Option[String]] and
      (JsPath \ "logo").read[Option[String]]
    )(unlift(Company.unapply))

  implicit val companyFormat: Format[Company] =
    Format(companyReads, companyWrites)
  */

  implicit val locationFormat: Format[Company] = (
    (JsPath \ "id").format[String] and
      (JsPath \ "name").format[String] and
      (JsPath \ "description").format[String] and
      (JsPath \ "logo").format[String]
    )(Company.apply, unlift(Company.unapply))

  def index = Action.async { implicit request =>
    companyDAO.all.map {
      companies =>
        println(companies)
        Ok(Json.toJson(companies))
    }
  }

  def show(id: String) = Action.async { implicit rs =>
    companyDAO.findById(id).map { event =>
      event.fold {
        NotFound(Json.toJson(
          ErrorResponse(NOT_FOUND, s"Invalid id: ${id}")
        ))
      } { company =>
        Ok(Json.toJson(
          SuccessResponse(company))
        )
      }
    }
  }


}

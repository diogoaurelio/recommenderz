package controllers.api.v1.backend

import javax.inject._
import controllers.api.v1.responses.{SuccessResponse, ErrorResponse}
import models.Company
import models.daos.CompanyDAO
import play.api.Logger
import play.api.Configuration
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Format, Json}
import play.api.mvc.{Action, Controller}
import scala.util.{Try, Success, Failure}

import scala.concurrent.{Future, ExecutionContext}


/**
  * Created by diogo on 08.10.16.
  */

@Singleton
class CompanyController @Inject()(configuration: Configuration, companyDAO: CompanyDAO)
                                 (implicit exec: ExecutionContext) extends Controller {

  val logger = Logger(this.getClass())

  implicit val companyFormat: Format[Company] = (
    (JsPath \ "id").format[String] and
      (JsPath \ "name").format[String] and
      (JsPath \ "description").format[String] and
      (JsPath \ "logo").format[String]
    )(Company.apply, unlift(Company.unapply))

  //GET     /api/v1/backend/companies/:id              controllers.api.v1.backend.CompanyController.show(id: String)
  def show(id: String) = Action.async { implicit rs =>
    companyDAO.findById(id).map { event =>
      event.fold {
        NotFound(Json.toJson(
          ErrorResponse(NOT_FOUND, s"Invalid id ${id}")
        ))
      } { company =>
        Ok(Json.toJson(SuccessResponse(company)))
      }
    }
  }

  // POST    /api/v1/backend/createCompany              controllers.api.v1.backend.CompanyController.create
  /*
  def create = Action.async { implicit rs =>
    val companyJson = Json.fromJson[Company](rs.request.body)
    val tryCreate = Try(companyDAO.insert(companyJson))
      tryCreate.map  match {
        case Success(company) => Created(Json.toJson(company)).withHeaders("Location" -> s"/movie/${company}")
        case Failure(exp) => BadRequest(s"Can't create movie: ${exp.getMessage}")
      }
  }
  */


  // POST    /api/v1/backend/companies/:id/update       controllers.api.v1.backend.CompanyController.update(id: String)
  def update(id: String) = TODO

  // POST    /api/v1/backend/companies/:id/delete       controllers.api.v1.backend.CompanyController.delete(id: String)
  def delete(id: String) = TODO


}

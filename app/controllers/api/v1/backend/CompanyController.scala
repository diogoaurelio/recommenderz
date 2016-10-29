package controllers.api.v1.backend

import java.util.concurrent.TimeoutException
import javax.inject._
import controllers.api.v1.responses.{SuccessResponse, ErrorResponse}
import models.Company
import models.daos.CompanyDAO
import play.api.Logger
import play.api.Configuration
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsError, JsPath, Format, Json}
import play.api.mvc.{AnyContent, BodyParsers, Action, Controller}
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

  def create = Action.async(BodyParsers.parse.json) { implicit rs =>
    val companyJson = rs.body.validate[Company]
    companyJson.fold(
      errors => {
        Future.successful(BadRequest(Json.obj("status" -> "KO", "message" -> JsError.toJson(errors))))
      },
      companyData => {
        val dbInsert = companyDAO.insert(companyData)
        dbInsert.map { result =>
          if (result == companyData.id)
            Ok(Json.obj("status" -> "OK",
              "message" -> ("Company '" + companyData.name + "' saved.")))
          else InternalServerError("Failed to save new company")

        }.recover {
          case ex:Throwable =>
            logger.error("[Dev Note]: Unable to insert new Company in DB - " + ex.getMessage)
            InternalServerError(ex.getMessage)
          case tex:TimeoutException =>
            logger.error("[Dev Note]: TimeoutException to insert new Company in DB - " + tex.getMessage)
            InternalServerError(tex.getMessage)

        }
      }
    )
  }


  // POST    /api/v1/backend/companies/:id/update       controllers.api.v1.backend.CompanyController.update(id: String)
  def update(id: String) = Action.async(BodyParsers.parse.json) { implicit rs =>
    val companyJson = rs.body.validate[Company]
    companyJson.fold(
      errors => {
        Future.successful(BadRequest(Json.obj("status" -> "KO", "message" -> JsError.toJson(errors))))
      },
      companyData => {
        val dbInsert = companyDAO.update(id, companyData)
        dbInsert.map { result =>
          if (result == companyData.id)
            Ok(Json.obj("status" -> "OK",
              "message" -> ("Company '" + companyData.name + "' saved.")))
          else InternalServerError("Failed to save new company")

        }.recover {
          case ex:Throwable =>
            logger.error("[Dev Note]: Unable to insert new Company in DB - " + ex.getMessage)
            InternalServerError(ex.getMessage)
          case tex:TimeoutException =>
            logger.error("[Dev Note]: TimeoutException to insert new Company in DB - " + tex.getMessage)
            InternalServerError(tex.getMessage)

        }
      }
    )
  }

  // POST    /api/v1/backend/companies/:id/delete       controllers.api.v1.backend.CompanyController.delete(id: String)
  def delete(id: String) = Action.async(BodyParsers.parse.json) { implicit rs =>
    companyDAO.delete(id).map { result =>
      Ok(Json.obj("status" -> "OK",
        "message" -> ("Successfully deleted Company with ID '" + id)))
    }.recover {
      case ex: TimeoutException =>
        Logger.error("Problem found in project delete process")
        InternalServerError(ex.getMessage)
    }
  }


}

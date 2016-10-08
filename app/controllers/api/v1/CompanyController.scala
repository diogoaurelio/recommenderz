package controllers.api.v1

import javax.inject._

import akka.actor.ActorSystem
import models.Company
import models.daos.CompanyDAO
import play.api.Configuration
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext

/**
  * Created by diogo on 19.10.16.
  *
  */

@Singleton
class CompanyController @Inject()(configuration: Configuration, companyDAO: CompanyDAO)
                                 (implicit exec: ExecutionContext) extends Controller {

  //val config = configuration.getString("my.config").getOrElse("none")

  implicit val companyFormat = Json.format[Company]

  def index = Action.async { implicit request =>
    companyDAO.all.map {
      jobs =>
        println(jobs)
        Ok(Json.toJson(jobs))
    }
  }

  def show(id: Long) = TODO


}

package controllers

import javax.inject.Inject
import models.ESJobPosition
import play.api.mvc.{Action, Controller}
import play.api.libs.json.Json
import models.daos.ESJobPositionDAO
import org.joda.time.DateTime
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by diogo on 26.08.16.
  */
class JobPositionController @Inject()(eSJobPositionDAO: ESJobPositionDAO) extends Controller {

  def get(jobPositionId: String) = Action.async {
    eSJobPositionDAO.getESJobPositionById(jobPositionId) map {
      case None => NotFound
      case Some(job) => Ok(Json.toJson(job))
    }
  }

  def search(q: String) = Action.async {
    eSJobPositionDAO.searchByQueryString(q) map {
      case jobs if jobs.length > 0 =>
        Ok(Json.toJson(jobs)).withHeaders("X-Total-Count" -> jobs.length.toString)
      case empty => NoContent
    }
  }

  def populate() = TODO



}

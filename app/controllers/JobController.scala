package controllers

import models.daos.JobPositionDAO
import models.JobPosition
import javax.inject._
import akka.actor.ActorSystem
import play.api.libs.json.{Writes, JsError, JsSuccess, Json}
import play.api.mvc.{Controller, Action}
import scala.concurrent.{ExecutionContext, Future, Promise}
import play.api.Configuration

/**
  * Created by diogo on 19.07.16.
  *
  */

@Singleton
class JobController @Inject() (configuration: Configuration, actorSystem: ActorSystem, jobPosDAO: JobPositionDAO)
                              (implicit exec: ExecutionContext) extends Controller {

  //val config = configuration.getString("my.config").getOrElse("none")

  def index = TODO


}

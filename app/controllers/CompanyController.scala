package controllers

import javax.inject._
import play.api.Logger
import play.api.Configuration
import play.api.mvc.{AnyContent, Action, Controller}

import scala.concurrent.{Future, ExecutionContext}

/**
  * Created by diogo on 19.07.16.
  *
  */

@Singleton
class CompanyController @Inject()(configuration: Configuration)
                                 (implicit exec: ExecutionContext) extends Controller {

  val logger = Logger(this.getClass())

  def index: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok(views.html.companies.index()))
  }

  def show(id: String): Action[AnyContent] = Action.async { implicit request =>
    logger.info("Entered Company view")
    Future.successful(Ok(views.html.companies.company()))
  }


}

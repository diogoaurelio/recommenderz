package controllers.backend

import javax.inject._
import play.api.Logger
import play.api.Configuration
import play.api.mvc.{AnyContent, Action, Controller}

import scala.concurrent.{Future, ExecutionContext}


/**
  *
  * Provides companies ability to view & edit their
  * own company data
  * Note: Controller associated with views
  */

@Singleton
class BECompanyController @Inject()(configuration: Configuration)
                                   (implicit exec: ExecutionContext) extends Controller {

  val logger = Logger(this.getClass())

  def index = TODO

  def show(id: String): Action[AnyContent] = TODO

  def add(): Action[AnyContent] = TODO

  def edit(id: String): Action[AnyContent] = TODO

}
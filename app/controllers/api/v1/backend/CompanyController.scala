package controllers.api.v1.backend

import javax.inject._
import play.api.Logger
import play.api.Configuration
import play.api.mvc.{AnyContent, Action, Controller}

import scala.concurrent.{Future, ExecutionContext}


/**
  * Created by diogo on 08.10.16.
  */

@Singleton
class CompanyController @Inject()(configuration: Configuration)
                                 (implicit exec: ExecutionContext) extends Controller {

  val logger = Logger(this.getClass())

  def index = TODO

  def show(id: Long): Action[AnyContent] = TODO

  def create = TODO
  def update(id: Long) = TODO
  def delete(id: Long) = TODO


}

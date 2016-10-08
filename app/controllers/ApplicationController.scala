package controllers


import javax.inject._
import play.api.mvc.{Controller, Action}
import play.api.{Configuration, Play}
import scala.concurrent.{Future, ExecutionContext}


/**
  * Created by diogo on 19.07.16.
  */
class ApplicationController @Inject()(configuration: Configuration)(implicit ec: ExecutionContext) extends Controller {


  def index() = Action.async { implicit request =>
      Future(Ok(views.html.index("Welcome to JobDating")))
  }

}

package controllers


import javax.inject._
import play.api.libs.json.Json
import play.api.mvc.{Controller, Action}
import play.api.{Configuration, Play}
import scala.concurrent.ExecutionContext


/**
  * Created by diogo on 19.07.16.
  */
class ApplicationController @Inject()(configuration: Configuration)(implicit ec: ExecutionContext) extends Controller {


  def index() = Action (
    Ok(views.html.index("Hello, we're rocking..."))
  )

}

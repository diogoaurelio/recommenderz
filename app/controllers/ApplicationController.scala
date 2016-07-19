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


  def api() = Action {
    val apiMap = Map(
      "apiKey"-> configuration.getString("jobdb.apiKey").getOrElse(""),
      "themoviedbUrl" -> "http://api.jobdb.org/3/search/movie?api_key=__apiKey__&query=__query__&page=__page__&language=en",
      "themoviedbDetailsUrl" -> "https://api.jobdb.org/3/movie/__themoviedbId__?api_key=__apiKey__",
      "baseUrl" -> "api",
      "favouriteListUrl" -> "list",
      "movieUrl" -> "movie"
    )

    Ok(Json.toJson(apiMap)).as(JSON)
  }
}

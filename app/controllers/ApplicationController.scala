package controllers

import akka.actor.ActorSystem
import javax.inject._
import play.api.mvc.{Controller, Action}
import com.mohiva.play.silhouette.api.{ Environment, Silhouette }
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.concurrent.duration._

/**
  * Created by diogo on 07.07.16.
  * TODO: , silhouette: Silhouette[MyEnv]
  * http://silhouette.mohiva.com/v4.0/docs/migration-guide
  */
@Singleton
class ApplicationController @Inject() (actorSystem: ActorSystem, silhouette: Silhouette[Environment])(implicit exec: ExecutionContext) extends Controller {

  def index = silhouette.SecuredAction { implicit request =>
    Ok(views.html.index(request.identity))
  }

  /*
  private def intensiveComputation(delayTime: FiniteDuration = 5.second): Future[Int] =  {
    val promise: Promise[Int] = Promise[Int]()
    actorSystem.scheduler.scheduleOnce(delayTime) { promise.success(42)}
    promise.future
  }

  def index2 = Action.async {
    val futureInt = Future { intensiveComputation(2.second) }
    //futureInt.map(i => Ok("Got result: " + i))
    //val futureInt2 = Future { 42 }
    val timeoutFuture = play.api.libs.concurrent.Promise.timeout("Oops", 1.second)
    Future.firstCompletedOf(Seq(futureInt, timeoutFuture)).map {
      case i: Int => Ok("Got result: " + i)
      case t: String => InternalServerError(t)
    }
  }
  */
}

package controllers
import javax.inject.Inject

import scala.concurrent.Future
import play.api.mvc.{Action, Controller}
import play.api.Logger
import com.evojam.play.elastic4s.PlayElasticFactory
import com.evojam.play.elastic4s.configuration.ClusterSetup
import com.sksamuel.elastic4s.ElasticDsl
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by diogo on 26.08.16.
  */
class ElasticController @Inject() (cs: ClusterSetup, elasticFactory: PlayElasticFactory) extends Controller with ElasticDsl {
  private val logger = Logger(getClass)
  private lazy val client = elasticFactory(cs)

  def getStats = Action.async {
    try {
      client execute {
        get cluster stats
      } map(response => Ok(response.toString))
    } catch {
      case e:Exception =>
        logger.error("Error connecting to ElasticSearch", e)
        Future(InternalServerError("Error connecting to ElasticSearch (hint: double-check application.conf is filled properly)"))
    }
  }

  def createIndex = Action.async {
    client execute {
      create index "library" replicas 0
    } map { _ => Ok("Index created")}
  }
}

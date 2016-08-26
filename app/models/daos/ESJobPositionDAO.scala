package models.daos

import javax.inject.{Named, Inject}

import com.evojam.play.elastic4s.{PlayElasticJsonSupport, PlayElasticFactory}
import com.evojam.play.elastic4s.configuration.ClusterSetup
import com.sksamuel.elastic4s.{ElasticDsl, IndexAndType}
import models.ESJobPosition

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by diogo on 26.08.16.
  */
class ESJobPositionDAO @Inject()(cs:ClusterSetup, elasticFactory: PlayElasticFactory, @Named("ESJobPosition") indexAndType: IndexAndType) extends ElasticDsl with PlayElasticJsonSupport {

  private[this] lazy val client = elasticFactory(cs)

  def getESJobPositionById(jobId:String)(implicit ec:ExecutionContext):Future[Option[ESJobPosition]] = client execute {
    get id jobId from indexAndType
  } map(_.as[ESJobPosition])


  def indexESJobPosition(jobId:String, jobPosition: ESJobPosition) = client execute { index into indexAndType source jobPosition id jobId }

  def bulkIndex(jobPositions: Iterable[ESJobPosition]) = client execute { bulk { jobPositions map (jobPosition => index into indexAndType source jobPosition) }}

  def searchByQueryString(q: String)(implicit ec: ExecutionContext) = client execute { search in indexAndType query queryStringQuery(q)} map(_.as[ESJobPosition])


}

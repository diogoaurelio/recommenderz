package utils
import configuration.AppConfig
import com.sksamuel.elastic4s.{RichSearchResponse, ElasticClient}
import com.sksamuel.elastic4s.ElasticDsl._
import scala.concurrent.Future
import org.elasticsearch.action.search.SearchResponse

/**
  * Created by diogo on 24.08.16.
  */
object ESClient {
  val ESConfig = AppConfig.ES

  /**
    * Search for an item in the index
    *
    * @param q
    * @return
    */
  def searchItem(q: String): Future[RichSearchResponse] = {
    val client = ElasticClient.remote(ESConfig.server, ESConfig.port)
    val productsIndex = ESConfig.productsIndex
    val rs = client.execute { search in productsIndex / "items" query q }
    rs
  }
}




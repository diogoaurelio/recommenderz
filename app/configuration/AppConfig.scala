package configuration
import play.api.Play
/**
  * Created by diogo on 24.08.16.
  */


object AppConfig {
  lazy val c = Play.current.configuration

  object ES {
    val server = c.getString("elasticsearch.server").get
    val port = c.getInt("elasticsearch.port").get
    val productsIndex = c.getString("elasticsearch.products_index").get
  }

  object DBConfig {
    val dbHost = c.getString("mongodb.host").get
    val dbPort = c.getInt("mongodb.port").get
    val dbName = c.getString("mongodb.database").get
  }

  object RS {
    val host = c.getString("recommenderService.host").get
    val port = c.getInt("recommenderService.port").get
  }

}


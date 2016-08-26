package models

import play.api.libs.json.Json
import org.joda.time.DateTime

/**
  * Created by diogo on 26.08.16.
  */
case class ESJobPosition(company:String, title:String, description:String, validity:Boolean, publishDate: DateTime, tags:Set[String]) {
  require(company != null, "company cannot be null")
  require(title != null, "title cannot be null")
  require(validity != null)
  require(publishDate != null)
}

object ESJobPosition {
  implicit val format = Json.format[ESJobPosition]
}

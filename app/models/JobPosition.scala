package models
import org.joda.time.DateTime
/**
  * Created by diogo on 19.07.16.
  */
case class JobPosition(
                  id: Long,
                  title: String,
                  description: Option[String],
                  company_id: Long,
                  tags: Option[String],
                  date_created: DateTime,
                  date_closed: DateTime
                  )

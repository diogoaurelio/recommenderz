package models

/**
  * Created by diogo on 19.07.16.
  */
case class Company(
                  id: Long,
                  name: String,
                  description: Option[String],
                  logo: Option[String]
                  )



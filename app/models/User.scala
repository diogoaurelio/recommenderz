package models

/**
  * Created by diogo on 19.07.16.
  */
case class User (
                id: Option[Long],
                name: String,
                email: Option[String],
                skills: Option[String]
                )

package models

import play.api.libs.json.{Json, JsPath, Writes, Reads, Format}
import play.api.libs.functional.syntax._
/**
  * Created by diogo on 19.07.16.
  */
case class Company(
                  id: String,
                  name: String,
                  description: String,
                  logo: String
                  )



package controllers.api.v1.responses


import play.api.libs.json.{ Json, Format, JsValue, JsNull, Writes }
/**
  * Created by diogo on 10.10.16.
  */

case class ErrorResult(status: Int, message: String)
object ErrorResult {
  implicit val format: Format[ErrorResult] = Json.format[ErrorResult]
}

case class EndpointResponse(result:String, response:JsValue, error:Option[ErrorResult])
object EndpointResponse {
  implicit val format: Format[EndpointResponse] = Json.format[EndpointResponse]
}

object ErrorResponse {
  def apply(status: Int, message: String) = {
    EndpointResponse("KO", JsNull, Option(ErrorResult(status, message)))
  }
}

object SuccessResponse {
  def apply[A](successResponse: A)(implicit w: Writes[A]) = {
    EndpointResponse("OK", Json.toJson(successResponse), None)
  }
}

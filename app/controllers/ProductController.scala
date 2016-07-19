package controllers
import play.api.mvc.Controller
import play.api.mvc.Action
import javax.inject._
import play.api.libs.json.Json

/**
  * Created by diogo on 19.07.16.
  *
  */
class ProductController @Inject() extends Controller {

  def index = Action.async {
    productsDAO.getAll.map(products => Ok(Json.toJson(products)))
  }

}

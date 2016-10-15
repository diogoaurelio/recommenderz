import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Bad Route /boum" should {

    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }

  }

  "HomeController#index" should {

    "render the index page" in {
      val home = route(app, FakeRequest(GET, "/")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      //contentAsString(home) must include ("Your new application is ready.")
    }

  }

  "/api/v1/companies/findById#fakeId" should {
    // TODO: mock DB
    "send 404 on a bad request" in {
      val result = route(app, FakeRequest(GET, "/api/v1/companies/fakeId")).get
      status(result) mustBe NOT_FOUND
      contentType(result) mustBe Some("application/json")
    }
  }

  "CountController" should {

    "return an increasing count" in {
      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "0"
      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "1"
      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "2"
    }

  }

  "CompanyController" should {

    "render the index page" in {
      val comp = route(app, FakeRequest(GET, "/companies")).get

      status(comp) mustBe OK
      contentType(comp) mustBe Some("text/html")
      //contentAsString(comp) must include ("Company Page")
    }
  }

}

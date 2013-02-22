import com.sun.jersey.api.client.{ClientResponse, Client}
import org.specs2.mutable.{BeforeAfter, Specification}

class HelloServletSpec extends Specification {

  "GET / on HelloServlet with world configuration" should {
    "greet the world" in new WorldServer {
      val greeting = get("http://localhost:" + server.port)
      greeting must equalTo("Hello World")
    }
  }

  "GET / on HelloServlet with galaxy configuration" should {
    "greet the galaxy" in new GalaxyServer {
      val greeting = get("http://localhost:" + server.port)
      greeting must equalTo("Hello Andromeda")
    }
  }

  def get(url: String): String =
    Client.create().resource(url: String).get(classOf[ClientResponse]).getEntity(classOf[String])
}

trait GalaxyServer extends BeforeAfter {
  val server = new TestServer(spaceObject = "Andromeda")
  def before { server.run }
  def after { server.stop }
}

trait WorldServer extends BeforeAfter {
  val server = new TestServer(spaceObject = "World")
  def before { server.run }
  def after { server.stop }
}

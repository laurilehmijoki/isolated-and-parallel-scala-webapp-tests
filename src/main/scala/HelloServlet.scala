import org.scalatra._

class HelloServlet(galacticObject: String) extends ScalatraServlet {

  get("/") {
    "Hello " + galacticObject
  }
}

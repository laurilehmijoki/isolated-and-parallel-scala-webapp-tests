import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    val personToGreet = System.getProperty("user.name")
    context.mount(new HelloServlet(personToGreet), "/*")
  }
}

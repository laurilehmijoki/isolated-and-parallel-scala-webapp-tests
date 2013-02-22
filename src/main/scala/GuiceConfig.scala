import com.google.inject.Guice
import com.google.inject.servlet.{GuiceServletContextListener, ServletModule}
import GuiceConfig._

class GuiceConfig extends GuiceServletContextListener {
  def getInjector = Guice.createInjector(servletModule)
}

object GuiceConfig {
  def servletModule = new ServletModule() {
    override def configureServlets() {
      val objectToGreet = System.getProperty("hello.target", System.getProperty("user.name"))
      serve("/*").`with`(new HelloServlet(objectToGreet))
    }
  }
}

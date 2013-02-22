import com.google.inject.servlet.ServletModule

object GuiceConfig {
  def servletModule = new ServletModule() {
    override def configureServlets() {
      val objectToGreet = System.getProperty("hello.target")
      serve("/*").`with`(new HelloServlet(objectToGreet))
    }
  }
}

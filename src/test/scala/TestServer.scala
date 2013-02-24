import com.google.inject.{Injector, Guice}
import com.google.inject.servlet.{GuiceFilter, GuiceServletContextListener}
import java.util
import javax.servlet.DispatcherType
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.FilterHolder
import org.eclipse.jetty.webapp.WebAppContext
import util.concurrent.atomic.AtomicInteger
import TestServer._

class TestServer(val port: Int = nextPort.getAndIncrement, spaceObject: String) {
  val server = new Server(port)

  def run() {
    start()
  }

  def stop() {
    server.stop()
    server.join()
    println("Shutting down server at port " + port)
  }

  private def start() {
    val injector = createInjector()
    server.setHandler(buildContext(injector))
    server.start()
    println("Started server at port " + port)
  }

  private def createInjector() = TestServer.synchronized {
    System.setProperty("hello.target", spaceObject)
    Guice.createInjector(GuiceConfig.servletModule)
  }

  private def buildContext(injector: Injector) = {
    val webapp = new WebAppContext()
    webapp.setResourceBase(".")
    webapp.setContextPath("/")
    webapp.addFilter(
      new FilterHolder(injector.getInstance(classOf[GuiceFilter])),
      "/*", util.EnumSet.of(DispatcherType.REQUEST)
    )
    webapp.addEventListener(new GuiceServletContextListener() {
      def getInjector = injector
    })
    webapp
  }
}

object TestServer {
  val nextPort = new AtomicInteger(8080)
}

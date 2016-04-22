package domofon

import akka.actor.ActorSystem
import akka.http.scaladsl._
import akka.http.scaladsl.server._
import akka.stream._
import ch.megard.akka.http.cors.CorsDirectives._

import scala.util.{Failure, Success}

object Main extends App with Helpers with DomofonService with Module {

  val configuration = parametersParser.parse(args, Config()).getOrElse {
    println("Unable to parse parameters")
    sys.exit(1)
  }

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  import system.dispatcher

  val host = configuration.listen.authority.host.address()
  val port = configuration.listen.authority.port
  val serverHostnameAndPort: String = s"${getNiceHostname(host)}:${port}"
  val serverUrl = s"http://${serverHostnameAndPort}"

  val routes: Route = handleRejections(corsRejectionHandler) {
    cors(corsSettings) {
      domofonRoute
    }
  }

  println(s"Will try to bind on ${host}:${port}")
  Http().bindAndHandle(routes, host, port).onComplete {
    case Success(binding) =>
      println(s"Listening on ${binding.localAddress}")
      println()
      println(s"Open $serverUrl")
      println()
      println()
      println("To use Swagger Editor (preferred):")
      println(s"http://editor.swagger.io/#/?import=${serverUrl}/domofon.yaml&no-proxy")
      println()
      println("To use Swagger UI:")
      println(s"http://blstream.github.io/domofon-api/#swagger=${serverUrl}/domofon.yaml")
      println()
      println()

    case Failure(e) =>
      println("Unable to bind, exiting...")
      e.printStackTrace()
      sys.exit(1)
  }

}

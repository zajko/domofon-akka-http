package domofon

import akka.http.scaladsl.model.{HttpMethods, Uri}
import ch.megard.akka.http.cors.CorsSettings

import scala.collection.immutable

trait Helpers {

  case class Config(listen: Uri = Uri("http://0.0.0.0:8080/"))

  implicit val scoptUriReader = new scopt.Read[akka.http.scaladsl.model.Uri] {
    override def arity: Int = 1

    override def reads: (String) => Uri = Uri(_)
  }

  def parametersParser = new scopt.OptionParser[Config]("domofon-akka-http") {
    head("domofon-akka-http")

    opt[Uri]('l', "listen") action { (x, c) =>
      c.copy(listen = x)
    } text ("Address to listen in http://hostname:port/ format")

    help("help") text "Show usage help"

    override def showUsageOnError = true
  }

  def getNiceHostname(address: String): String = {
    if (address.matches("^[0:\\.]*$")) {
      "localhost"
    } else {
      address
    }
  }

  def corsSettings = CorsSettings.defaultSettings.copy(allowedMethods = immutable.Seq(
    HttpMethods.GET, HttpMethods.POST, HttpMethods.PUT, HttpMethods.DELETE
  ))

}

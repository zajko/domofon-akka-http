package domofon.routes

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import domofon.DB
import domofon.entities.IsImportant
import spray.json.DefaultJsonProtocol

object Importants extends SprayJsonSupport with Directives with DefaultJsonProtocol{

  val isImportantFormat = jsonFormat1(IsImportant.apply)
  implicit val operarationStatusMarshaller: ToEntityMarshaller[IsImportant] =
    Marshaller.oneOf(
      isImportantFormat,
      Marshaller.StringMarshaller.wrap(MediaTypes.`text/plain`) {
        is => is.isImportant.toString
      }
    )

  def getImportantContact: Route = path("contacts"/ Segment / "important") { id =>
    get {
      checkImportance(id)
    }
  }

  def putImportantContact: Route = complete(StatusCodes.NotImplemented)

  def checkImportance(id: String): Route = {
    DB.contacts.get(id) match {
      case Some(c) => complete(IsImportant(c.isImportant))
      case _ => complete(StatusCodes.NotFound, "Unable to find Contact entity")
    }
  }
}

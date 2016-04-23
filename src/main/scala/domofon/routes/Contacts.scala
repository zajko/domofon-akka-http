package domofon.routes

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model.MediaTypes
import akka.http.scaladsl.server.{Route, Directives}
import domofon.DB
import domofon.entities.{Notifee, Contact}
import spray.json._

/**
 * @author zajkowsj
 */
object Contacts extends SprayJsonSupport with Directives with DefaultJsonProtocol {
  implicit val notifeeFormat = jsonFormat4(Notifee.apply)
  implicit val notifeeMarshaller: ToEntityMarshaller[Notifee] =
    Marshaller.oneOf(
      notifeeFormat
    )
  implicit val contactFormat = jsonFormat12(Contact.apply)
  implicit val contactMarshaller: ToEntityMarshaller[Contact] =
    Marshaller.oneOf(
      contactFormat
    )

  def getContacts: Route = path("contacts") {
    get{
      parameter("query".as[String].?){
        query => {query match {
          case None => complete(DB.contacts.values.toList.toJson)
          case Some(q) => {
            val regex = q.r
            complete(DB.contacts.values.filter(contact => regex.pattern.matcher(contact.category).matches).toList.toJson)
          }
        }}
      }
    }
  }
}

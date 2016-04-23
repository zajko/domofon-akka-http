package domofon

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server._
import akka.stream.Materializer
import domofon.routes.Importants
import domofon.routes.Categories
import domofon.entities.{Contact, IsImportant}
import spray.json.DefaultJsonProtocol

import scala.concurrent.ExecutionContext

trait DomofonService extends SwaggerRoute with SprayJsonSupport with Directives with DefaultJsonProtocol {

  implicit def system: ActorSystem

  implicit def materializer: Materializer

  implicit def executionContext: ExecutionContext = system.dispatcher

  def domofonRoute: Route = {
    domofonYmlRoute ~
      getImportantContact ~
      complete(StatusCodes.NotImplemented)
  }

  def getContact: Route = complete(StatusCodes.NotImplemented)

  def postContact: Route = complete(StatusCodes.NotImplemented)

  def getContactSSE: Route = complete(StatusCodes.NotImplemented)

  def getContactById: Route = complete(StatusCodes.NotImplemented)

  def deleteContactById: Route = complete(StatusCodes.NotImplemented)

  def notifyContact: Route = complete(StatusCodes.NotImplemented)

  def getImportantContact: Route = Importants.getImportantContact

  def putImportantContact: Route = Importants.putImportantContact

  def deleteDeputy: Route = complete(StatusCodes.NotImplemented)

  def putDeputy: Route = complete(StatusCodes.NotImplemented)

  def getCategories: Route = Categories.getCategories

  def postCategory: Route = Categories.postCategory

  def notifyCategory: Route = complete(StatusCodes.NotImplemented)

  def getCategoryMessages: Route = complete(StatusCodes.NotImplemented)

  def postCategoryMessage: Route = complete(StatusCodes.NotImplemented)

  def deleteCategoryMessage: Route = complete(StatusCodes.NotImplemented)

  def putCategoryMessage: Route = complete(StatusCodes.NotImplemented)

  def login: Route = complete(StatusCodes.NotImplemented)
}

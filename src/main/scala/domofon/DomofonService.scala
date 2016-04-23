package domofon

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import akka.stream.Materializer
import domofon.routes.Categories

import scala.concurrent.ExecutionContext

trait DomofonService extends SwaggerRoute with SprayJsonSupport with Directives {

  implicit def system: ActorSystem

  implicit def materializer: Materializer

  implicit def executionContext: ExecutionContext = system.dispatcher

  def domofonRoute: Route = {
    domofonYmlRoute ~
      getContact ~
      postContact ~
      getContactSSE ~
      getContactById ~
      deleteContactById ~
      notifyContact ~
      getImportantContact ~
      putImportantContact ~
      deleteDeputy ~
      putDeputy ~
      getCategories ~
      complete(StatusCodes.NotImplemented)
  }

  def getContact: Route = complete(StatusCodes.NotImplemented)

  def postContact: Route = complete(StatusCodes.NotImplemented)

  def getContactSSE: Route = complete(StatusCodes.NotImplemented)

  def getContactById: Route = complete(StatusCodes.NotImplemented)

  def deleteContactById: Route = complete(StatusCodes.NotImplemented)

  def notifyContact: Route = complete(StatusCodes.NotImplemented)

  def getImportantContact: Route = complete(StatusCodes.NotImplemented)

  def putImportantContact: Route = complete(StatusCodes.NotImplemented)

  def deleteDeputy: Route = complete(StatusCodes.NotImplemented)

  def putDeputy: Route = complete(StatusCodes.NotImplemented)

  def getCategories: Route = Categories.getCategories

  def postCategory: Route = Categories.postCategory
}

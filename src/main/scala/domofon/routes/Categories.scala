package domofon.routes

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._

object Categories extends SprayJsonSupport with Directives {

  def getCategories: Route = complete(StatusCodes.NotImplemented)

  def postCategory: Route = complete(StatusCodes.NotImplemented)

}

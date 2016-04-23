package domofon.routes

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import domofon.DB
import domofon.entities.{CategoryMessage, CategoryResponse}
import spray.json.DefaultJsonProtocol

object Categories extends SprayJsonSupport with Directives with DefaultJsonProtocol {
  implicit val categoryResponseFormat = jsonFormat6(CategoryResponse.apply)
  implicit val messageFormat = jsonFormat2(CategoryMessage.apply)

  def getCategories: Route = path("categories") {
    get {
      complete {
        val categories = DB.categories.values.toSeq

        "foo"
      }
    }
  }
  def postCategory: Route = complete(StatusCodes.NotImplemented)
}

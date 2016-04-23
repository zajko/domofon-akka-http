package domofon.routes

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import domofon.DB
import domofon.entities.{CategoryMessage, CategoryResponse}
import spray.json.{DefaultJsonProtocol, _}

object Categories extends SprayJsonSupport with Directives with DefaultJsonProtocol {
  private implicit val messageFormat = jsonFormat2(CategoryMessage.apply)
  private implicit val categoryResponseFormat = jsonFormat6(CategoryResponse.apply)

  def getCategories: Route = path("categories") {
    get {
      val categoriesResponses = DB.categories.values.toList
      complete {
        categoriesResponses.toJson
      }
    }
  }

  def postCategory: Route = complete(StatusCodes.NotImplemented)
}

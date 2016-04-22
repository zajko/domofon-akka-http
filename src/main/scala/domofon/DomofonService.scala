package domofon

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import akka.stream.Materializer

import scala.concurrent.ExecutionContext

trait DomofonService extends SwaggerRoute with SprayJsonSupport with Directives {

  implicit def system: ActorSystem

  implicit def materializer: Materializer

  implicit def executionContext: ExecutionContext = system.dispatcher

  def domofonRoute: Route = {
    domofonYmlRoute ~
      complete(StatusCodes.NotImplemented)
  }

}

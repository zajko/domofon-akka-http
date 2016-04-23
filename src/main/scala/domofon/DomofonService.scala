package domofon

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.marshalling._
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import akka.stream.Materializer
import spray.json._
import scala.concurrent.{Future, ExecutionContext}

trait DomofonService extends SwaggerRoute with SprayJsonSupport with Directives with DefaultJsonProtocol {

  implicit def system: ActorSystem

  implicit def materializer: Materializer

  implicit def executionContext: ExecutionContext = system.dispatcher

  case class DomainValidationRejection(reason: String) extends Rejection

  val rejectionHandler = RejectionHandler.newBuilder().handle {
    case DomainValidationRejection(reason) => complete(
      (StatusCodes.BadRequest, s"It is not allowed to use something else: ${reason}")
    )
  }.result()

  case class Req(test: String, op: Option[String] = None)

  case class OperationsStatus(status: String)

  def doSomeOperation(req: Req): OperationsStatus = {
    OperationsStatus(req.test)
  }

  def doAsyncOperation(req: Req): Future[OperationsStatus] = {
    Future {
      OperationsStatus(req.test)
    }
  }

  implicit val reqFormat = jsonFormat2(Req.apply)
  val operationsStatusFormat = jsonFormat1(OperationsStatus.apply)
  implicit val operarationStatusMarshaller: ToEntityMarshaller[OperationsStatus] =
    Marshaller.oneOf(
      Marshaller.StringMarshaller.wrap(MediaTypes.`text/plain`)(os => os.status),
      Marshaller.StringMarshaller.wrap(MediaTypes.`application/xml`)(
        os => s"<test>${os.status}</test>"
      ),
      operationsStatusFormat
    )

  def domofonRoute: Route = handleRejections(rejectionHandler) {
    extractRequest { req =>
      println(req)
      domofonYmlRoute ~
        pathPrefix("contacts") {
          path("notify" / Segment) { s =>
            if (s == "lol")
              complete("notify")
            else reject(DomainValidationRejection("notlol"))
          } ~ path("whatever") {
            complete("w")
          } ~ pathEndOrSingleSlash {
            get {
              parameter("category".as[String].?) { category =>
                complete(s"I got ${category}")
              }
            } ~ post {
              entity(as[Req]) { ent =>
                complete(doSomeOperation(ent))
              } ~ entity(as[String]) { str =>
                complete("I'm string")
              }
            } ~
              put {
                entity(as[Req]) { ent =>
                  complete(doAsyncOperation(ent))
                } ~ entity(as[String]) { str =>
                  complete("I'm string")
                }

              } ~ reject()
          }
        }
    }
  }

}

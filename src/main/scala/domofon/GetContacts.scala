package domofon

import akka.http.scaladsl.server._
import akka.http.scaladsl.server._
import domofon.entities.Contact

/**
 * @author zajkowsj
 */
object GetContacts {

  def getContacts: List[Contact] = (query: Option[String]) => {

  }
}

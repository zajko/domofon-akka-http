package domofon

import domofon.entities.{Contact, ContactRequest}

/**
 * @author zajkowsj
 */
object DB {
  val contacts = collection.mutable.Map[String, Contact]();
}

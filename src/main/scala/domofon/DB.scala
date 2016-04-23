package domofon

import domofon.entities.ContactRequest

/**
 * @author zajkowsj
 */
object DB {
  val contacts = collection.mutable.Map[String, ContactRequest]();
}

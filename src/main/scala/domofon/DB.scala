package domofon

import domofon.entities.{CategoryResponse, Contact, ContactRequest}

/**
 * @author zajkowsj
 */
object DB {
  val contacts = collection.mutable.Map[String, Contact]();
  val categories = collection.mutable.Map[String, CategoryResponse]();
}

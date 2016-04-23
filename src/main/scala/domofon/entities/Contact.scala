package domofon.entities

/**
 * @author zajkowsj
 */

case class ContactRequest( name: String, category: String, company: Option[String], notifyEmail: String, phone: Option[String], adminEmail: Option[String], fromDate : Option[String], tillDate: Option[String])
case class Contact (id: String, name: String, category: String, company: Option[String], notifyEmail: String, fromDate: Option[String], tillDate: String, isImportant: Boolean, nextNotificationAllowedAt: Option[String] )


case class EntityCreatedWithSecret(id: String, secret: String)

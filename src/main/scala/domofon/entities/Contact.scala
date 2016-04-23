package domofon.entities

/**
 * @author zajkowsj
 */

case class CategoryResponse (id: String, name: String, description: String, isIndividual: Boolean, messages: List[String], nextNotificationAllowedAt: Option[String])
case class CategoryRequest (name: String, description: String, isIndividual: Option[Boolean], message: String)
case class CategoryMessage (id: String, message: String)

case class Notifee(name: String, company: Option[String], notifyEmail: String, phone: String)
case class ContactRequest( name: String, category: String, company: Option[String], notifyEmail: String, phone: Option[String], adminEmail: Option[String], fromDate : Option[String], tillDate: Option[String])
case class Contact (id: String, name: String, category: String, company: Option[String], notifyEmail: String, String, phone: Option[String], adminEmail: Option[String], fromDate: Option[String], tillDate: String, isImportant: Boolean, nextNotificationAllowedAt: Option[String], deputy: Notifee)
case class Error(code: Option[Int], message: String, fields: List[String])
case class NotificationRetry(message: String, whenAllowed: String)


case class EntityCreated(id: String)
case class EntityCreatedWithSecret(id: String, secret: String)
case class IsImportant(isImportant: Boolean)
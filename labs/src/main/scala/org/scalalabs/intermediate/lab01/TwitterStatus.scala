package org.scalalabs.intermediate.lab01

import java.util.Locale

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


abstract class TwitterStatus {

  val id: Long
  val text: String
  val source: String
  val truncated: Boolean
  val inReplyToStatusId: Option[Long]
  val inReplyToUserId: Option[Long]
  val favorited: Boolean
  val inReplyToScreenName: Option[String]
  val user: TwitterUser
  val createdAt : DateTime
}

object TwitterStatus {
  def apply(node: scala.xml.Node): TwitterStatus = {
    new TwitterStatus {
      val twitterDateTimeFormat = DateTimeFormat.forPattern("EE MMM dd HH:mm:ss Z yyyy").withLocale(Locale.US)
      val createdAt = twitterDateTimeFormat.parseDateTime((node \ "created_at").text)
      val id = (node \ "id").text.toLong
      val text = (node \ "text").text
      val source = (node \ "source").text
      val truncated = (node \ "truncated").text.toBoolean
      val inReplyToStatusId = {
        val value = (node \ "in_reply_to_status_id").text
        if (value == "") None else Some(value.toLong)
      }
      val inReplyToUserId = {
        val value = (node \ "in_reply_to_user_id").text
        if (value == "") None else Some(value.toLong)
      }
      val favorited = (node \ "favorited").text.toBoolean
      val user = TwitterUser((node \ "user")(0))
      val inReplyToScreenName = {
        val value = (node \ "in_reply_to_screen_name").text
        if (value == "") None else Some(value)
      }
    }
  }
}

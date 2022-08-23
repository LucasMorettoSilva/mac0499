package br.usp.syncmsconnection.model.entity

import io.swagger.v3.oas.annotations.media.Schema
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "messages")
@Schema(description = "model representing a message sent in a chat")
class Message(

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    @field:Schema(
        description = "message id in UUID format",
        example = "54b2a47a-7153-4925-8011-b23985cbcd4d"
    )
    var id: String? = null,

    @Column(name = "chat_id")
    @field:Schema(
        description = "chat id in UUID format",
        example = "54b2a47a-7153-4925-8011-b23985cbcd4d"
    )
    var chatId: String? = null,

    @Column(name = "sender")
    @field:Schema(
        description = "user email",
        example = "user1@email.com"
    )
    var sender: String? = null,

    @Column(name = "content")
    @field:Schema(
        description = "message text content",
        example = "hey, how are you?"
    )
    var content: String? = null,

    @CreationTimestamp
    @Column(name = "creation_date")
    @field:Schema(
        description = "creation timestamp in ISO-8601 format",
        example = "2007-12-03T10:15:30"
    )
    var creationDate: LocalDateTime? = null
)

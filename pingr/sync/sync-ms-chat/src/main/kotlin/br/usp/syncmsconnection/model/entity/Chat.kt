package br.usp.syncmsconnection.model.entity

import io.swagger.v3.oas.annotations.media.Schema
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "chats")
@Schema(description = "model for chat representation")
class Chat(

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    @field:Schema(
        description = "chat id in UUID format",
        example = "54b2a47a-7153-4925-8011-b23985cbcd4d"
    )
    var id: String? = null,

    @CreationTimestamp
    @Column(name = "creation_date")
    @field:Schema(
        description = "creation timestamp in ISO-8601 format",
        example = "2007-12-03T10:15:30"
    )
    var creationDate: LocalDateTime? = null
)

package br.usp.syncmsconnection.model.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "messages")
class Message(

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    var id: String? = null,

    @Column(name = "chat_id")
    var chatId: String? = null,

    @Column(name = "user_email")
    var userEmail: String? = null,

    @Column(name = "content")
    var content: String? = null,

    @CreationTimestamp
    @Column(name = "creation_date")
    var creationDate: LocalDateTime? = null
)

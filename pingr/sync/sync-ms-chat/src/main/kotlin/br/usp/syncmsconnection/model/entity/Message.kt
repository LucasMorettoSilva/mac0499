package br.usp.syncmsconnection.model.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "messages")
class Message(

    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: UUID,

    @Column(name = "chat_id")
    var chatId: UUID,

    @Column(name = "user_email")
    var userEmail: String,

    @Column(name = "content")
    var content: String,

    @CreationTimestamp
    @Column(name = "creation_date")
    var creationDate: LocalDateTime
)

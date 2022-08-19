package br.usp.syncmsconnection.model.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "chats")
class Chat(

    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: UUID,

    @CreationTimestamp
    @Column(name = "creation_date")
    var creationDate: LocalDateTime
)

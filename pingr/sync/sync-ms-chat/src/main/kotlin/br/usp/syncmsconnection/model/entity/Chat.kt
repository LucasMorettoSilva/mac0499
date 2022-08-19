package br.usp.syncmsconnection.model.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "chats")
class Chat(

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    var id: String? = null,

    @CreationTimestamp
    @Column(name = "creation_date")
    var creationDate: LocalDateTime? = null
)

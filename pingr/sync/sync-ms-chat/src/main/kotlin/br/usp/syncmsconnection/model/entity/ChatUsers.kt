package br.usp.syncmsconnection.model.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table

@Entity
@IdClass(ChatUsersId::class)
@Table(name = "chats_users")
class ChatUsers(

    @Id
    @Column(name = "chat_id")
    var chatId: UUID,

    @Id
    @Column(name = "user_email")
    var userEmail: String
)

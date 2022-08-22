package br.usp.asyncmschat.model.entity

import javax.persistence.*

@Entity
@IdClass(ChatUsersId::class)
@Table(name = "chats_users")
class ChatUsers(

    @Id
    @Column(name = "chat_id")
    var chatId: String?,

    @Id
    @Column(name = "user_email")
    var userEmail: String?
)

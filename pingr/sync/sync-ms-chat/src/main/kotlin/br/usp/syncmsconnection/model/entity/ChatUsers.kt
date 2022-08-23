package br.usp.syncmsconnection.model.entity

import io.swagger.v3.oas.annotations.media.Schema
import javax.persistence.*

@Entity
@IdClass(ChatUsersId::class)
@Table(name = "chats_users")
@Schema(description = "model for relation between chat and user")
class ChatUsers(

    @Id
    @Column(name = "chat_id")
    @field:Schema(
        description = "chat id in UUID format",
        example = "54b2a47a-7153-4925-8011-b23985cbcd4d"
    )
    var chatId: String?,

    @Id
    @Column(name = "user_email")
    @field:Schema(
        description = "user email",
        example = "user1@email.com"
    )
    var userEmail: String?
)

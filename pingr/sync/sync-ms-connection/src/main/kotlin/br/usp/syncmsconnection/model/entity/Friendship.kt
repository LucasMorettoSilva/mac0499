package br.usp.syncmsconnection.model.entity

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "friendships")
@IdClass(FriendshipId::class)
@Schema(description = "model to establish friendship between users")
class Friendship(

    @Id
    @Email
    @NotBlank
    @Column(name = "user_email_1")
    @field:Schema(
        description = "email of first user",
        example = "user1@email.com"
    )
    var userEmail1: String,

    @Id
    @Email
    @NotBlank
    @Column(name = "user_email_2")
    @field:Schema(
        description = "email of second user",
        example = "user2@email.com"
    )
    var userEmail2: String
): Serializable

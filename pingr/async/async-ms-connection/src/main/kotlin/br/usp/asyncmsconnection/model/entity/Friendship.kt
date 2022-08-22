package br.usp.asyncmsconnection.model.entity

import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "friendships")
@IdClass(FriendshipId::class)
class Friendship(

    @Id
    @Email
    @NotBlank
    @Column(name = "user_email_1")
    var userEmail1: String,

    @Id
    @Email
    @NotBlank
    @Column(name = "user_email_2")
    var userEmail2: String
): Serializable

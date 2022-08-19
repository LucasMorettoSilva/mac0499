package br.usp.syncmsconnection.model.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "friendships")
@IdClass(ChatUsersId::class)
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
)

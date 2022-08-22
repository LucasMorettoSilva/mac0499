package br.usp.asyncmsconnection.model.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class FriendshipRequest(
    @NotBlank
    @Email
    val firstUserEmail: String,

    @NotBlank
    @Email
    val secondUserEmail: String)

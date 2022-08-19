package br.usp.syncmsconnection.model.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class MessageRequest(
    @NotBlank
    val message: String,

    @NotBlank
    @Email
    val senderEmail: String,

    @NotBlank
    @Email
    val recipientEmail: String
)

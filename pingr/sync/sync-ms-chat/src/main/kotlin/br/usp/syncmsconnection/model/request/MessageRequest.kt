package br.usp.syncmsconnection.model.request

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Schema(
    description = "model representing a request to send a message to an user"
)
data class MessageRequest(

    @NotBlank
    @field:Schema(
        description = "message text content",
        example = "hey, how are you?"
    )
    val message: String,

    @NotBlank
    @Email
    @field:Schema(
        description = "sender user email",
        example = "user1@email.com"
    )
    val senderEmail: String,

    @NotBlank
    @Email
    @field:Schema(
        description = "recipient user email",
        example = "user2@email.com"
    )
    val recipientEmail: String
)

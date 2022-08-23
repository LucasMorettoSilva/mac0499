package br.usp.syncmsconnection.model.entity

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "model to establish friendship between users")
class Friendship(

    @field:Schema(
        description = "email of first user",
        example = "user1@email.com"
    )
    var userEmail1: String,

    @field:Schema(
        description = "email of second user",
        example = "user2@email.com"
    )
    var userEmail2: String
)

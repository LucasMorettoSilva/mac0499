package br.usp.syncmsconnection.model.entity

import java.io.Serializable
import java.util.UUID
import javax.persistence.Embeddable
import javax.validation.constraints.Email

@Embeddable
data class ChatUsersId(
    var chatId: UUID,
    var userEmail: String) : Serializable

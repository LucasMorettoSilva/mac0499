package br.usp.syncmsconnection.model.entity

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
data class ChatUsersId(
    var chatId: String,
    var userEmail: String) : Serializable

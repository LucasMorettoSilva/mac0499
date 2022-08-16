package br.usp.syncmsconnection.model.entity

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
data class FriendshipId(
    var userEmail1: String,
    var userEmail2: String) : Serializable

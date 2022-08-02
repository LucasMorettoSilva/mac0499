package br.usp.models

import kotlinx.serialization.Serializable

@Serializable
data class MessageRequest(
    val messageSize: Double,
    val times: Int)

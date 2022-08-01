package br.usp.models

import kotlinx.serialization.Serializable

@Serializable
data class MessageRequest(
    val queueName: String,
    val messageSize: Double,
    val times: Int)

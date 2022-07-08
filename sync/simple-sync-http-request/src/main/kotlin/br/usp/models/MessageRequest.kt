package br.usp.models

import kotlinx.serialization.Serializable

@Serializable
data class MessageRequest(val sizeOfMessage: Double)

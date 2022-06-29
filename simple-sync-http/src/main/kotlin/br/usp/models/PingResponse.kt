package br.usp.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class PingResponse(
    val message: String,
    val totalTime: String)

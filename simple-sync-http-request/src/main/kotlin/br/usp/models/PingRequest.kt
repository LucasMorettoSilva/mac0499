package br.usp.models

import kotlinx.serialization.Serializable

@Serializable
data class PingRequest(val message: String)

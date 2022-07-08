package br.usp

import kotlinx.serialization.Serializable

@Serializable
data class PingRequest(val message: String)

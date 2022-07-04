package br.usp.config

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

fun getHttpClient(): HttpClient {
    return HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    }
}

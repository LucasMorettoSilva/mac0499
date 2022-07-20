package br.usp.plugins

import br.usp.routes.generatorRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        generatorRouting()
    }
}

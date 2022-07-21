package br.usp.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.pingRouting() {
    route("/ping") {
        post {
            val message = call.receive<String>()

            call.application.environment.log.info(
                "pingRouting() : received message of size : {}",
                message.length
            )

            call.respond(message)
        }
    }
}

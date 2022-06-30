package br.usp.routes

import br.usp.PingRequest
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.pingRouting() {
    route("/ping") {
        post {
            val pingRequest = call.receive<PingRequest>()

            call.application.environment.log.info(
                "pingRouting() : request : {}",
                pingRequest
            )

            call.respond(pingRequest)
        }
    }
}

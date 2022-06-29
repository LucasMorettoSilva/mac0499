package br.usp.routes

import br.usp.models.PingResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDateTime

fun Route.pingRouting() {
    route("/ping") {
        get {
            call.respond(
                PingResponse("done", LocalDateTime.now().toString())
            )
        }
    }
}

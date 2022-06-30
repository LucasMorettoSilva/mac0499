package br.usp.routes

import br.usp.models.MessageRequest
import br.usp.models.PingRequest
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.utils.io.core.*

fun Route.pingRouting() {
    route("/ping") {
        post {
            val messageReq = call.receive<MessageRequest>()

            call.application.environment.log.info(
                "pingRouting() : received message request of size {}",
                messageReq.sizeOfMessage
            )

            val client = HttpClient() {
                install(ContentNegotiation) {
                    json()
                }
            }

            val response: HttpResponse = client.post("http://localhost:9086/ping") {
                setBody(PingRequest("A".repeat(messageReq.sizeOfMessage.toInt())))
                contentType(ContentType.Application.Json)
            }

            call.application.environment.log.info(
                "successfully got response : http status code : {}",
                response.status
            )

            call.respond(HttpStatusCode.OK)
        }
    }
}

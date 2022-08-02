package br.usp.routes

import br.usp.config.getHttpClient
import br.usp.models.MessageRequest
import br.usp.utils.StringGenerator
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.pingRouting() {
    route("/ping") {
        post {
            val messageReq = call.receive<MessageRequest>()

            call.application.environment.log.info(
                "pingRouting() : received message request of size {} MB",
                messageReq.messageSize
            )

            val client = getHttpClient()

            val hostUrl = call.application.environment
                .config
                .property("api.host.simpleSyncHttpResponse")
                .getString()

            val pingEndpoint = call.application.environment
                .config
                .property("api.endpoint.ping")
                .getString()

            val message = StringGenerator
                .withSizeInMegaBytes(messageReq.messageSize)

            for (i in 1..messageReq.times) {
                val response: HttpResponse = client.post(hostUrl + pingEndpoint) {
                    setBody(message)
                    contentType(ContentType.Text.Plain)
                }

                if (response.status != HttpStatusCode.OK) {
                    call.application.environment.log.error(
                        "unexpected failure : http status code : {} : body : {}",
                        response.status,
                        response.bodyAsText()
                    )

                    call.respond(HttpStatusCode.InternalServerError)
                }
            }

            call.application.environment.log.info(
                "process finished"
            )

            client.close()

            call.respond(HttpStatusCode.OK)
        }
    }
}

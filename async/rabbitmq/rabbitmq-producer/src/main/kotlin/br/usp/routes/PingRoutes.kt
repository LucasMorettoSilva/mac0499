package br.usp.routes

import br.usp.models.MessageRequest
import br.usp.service.Producer
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
                "pingRouting() : message request : {}",
                messageReq
            )

            val brokerUrl = call.application.environment
                .config
                .property("api.host.rabbitBroker")
                .getString()

            val queueName = call.application.environment
                .config
                .property("amqp.queue")
                .getString()

            Producer.publish(brokerUrl, messageReq, queueName)

            call.application.environment.log.info(
                "pingRouting() : process finished"
            )

            call.respond(HttpStatusCode.OK)
        }
    }
}

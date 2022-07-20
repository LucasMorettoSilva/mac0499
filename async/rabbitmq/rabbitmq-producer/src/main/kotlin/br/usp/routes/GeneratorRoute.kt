package br.usp.routes

import br.usp.models.MessageRequest
import br.usp.utils.StringGenerator
import com.rabbitmq.client.ConnectionFactory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.nio.charset.StandardCharsets

fun Route.generatorRouting() {
    route("/generate") {
        post {
            val messageReq = call.receive<MessageRequest>()
            val message = StringGenerator
                .withSizeInMegaBytes(messageReq.sizeOfMessage)

            call.application.environment.log.info(
                "generatorRouting() : received message request of size {} MB",
                messageReq.sizeOfMessage
            )

            val hostUrl = call.application.environment
                .config
                .property("api.host.rabbitConsumer")
                .getString()

            call.application.environment.log.info(
                "generatorRouting() : hostUrl : {}",
                hostUrl
            )

            val factory = ConnectionFactory()

            factory.newConnection(hostUrl).use { connection ->
                connection.createChannel().use { channel ->
                    channel.queueDeclare(messageReq.queueName, false, false, false, null)

                    channel.basicPublish(
                        "",
                        messageReq.queueName,
                        null,
                        message.toByteArray(StandardCharsets.UTF_8)
                    )

                    call.application.environment.log.info(
                        "successfully sent message"
                    )
                }
            }

            call.respond(HttpStatusCode.OK)
        }
    }
}

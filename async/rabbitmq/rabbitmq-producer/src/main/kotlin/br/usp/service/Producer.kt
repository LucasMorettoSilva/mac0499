package br.usp.service

import br.usp.models.MessageRequest
import br.usp.utils.StringGenerator
import com.rabbitmq.client.ConnectionFactory
import io.ktor.utils.io.core.*
import java.nio.charset.StandardCharsets
import kotlin.text.toByteArray

class Producer {

    companion object {

        fun publish(brokerUrl: String, messageReq: MessageRequest) {
            val message = StringGenerator
                .withSizeInMegaBytes(messageReq.sizeOfMessage)

            val factory = ConnectionFactory()

            factory.newConnection(brokerUrl).use { connection ->
                connection.createChannel().use { channel ->
                    channel.queueDeclare(messageReq.queueName, false, false, false, null)

                    channel.basicPublish(
                        "",
                        messageReq.queueName,
                        null,
                        message.toByteArray(StandardCharsets.UTF_8)
                    )
                }
            }
        }
    }
}

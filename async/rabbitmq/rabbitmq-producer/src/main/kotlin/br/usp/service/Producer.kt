package br.usp.service

import br.usp.models.MessageRequest
import br.usp.utils.StringGenerator
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import java.nio.charset.StandardCharsets
import kotlin.text.toByteArray

class Producer {

    companion object {

        private var connection: Connection? = null

        fun publish(brokerUrl: String, messageReq: MessageRequest) {
            if (connection == null || !connection!!.isOpen) {
                val factory = ConnectionFactory()

                connection = factory.newConnection(brokerUrl)
            }

            val message = StringGenerator
                .withSizeInMegaBytes(messageReq.sizeOfMessage)

            connection!!.createChannel().use { channel ->
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

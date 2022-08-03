package br.usp.service

import br.usp.models.MessageRequest
import br.usp.utils.StringGenerator
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import java.nio.charset.StandardCharsets
import kotlin.text.toByteArray

class Producer {

    companion object {

        private var connection: Connection? = null

        private var channel: Channel? = null

        private fun createConnection(brokerUrl: String) {
            if (connection == null || !connection!!.isOpen) {
                val factory = ConnectionFactory()

                connection = factory.newConnection(brokerUrl)
            }
        }

        private fun establishConnection(brokerUrl: String, queueName: String) {
            if (channel == null || !channel!!.isOpen) {
                createConnection(brokerUrl)
                channel = connection?.createChannel()
                channel?.queueDeclare(queueName, false, false, false, null)
            }
        }

        fun publish(brokerUrl: String, messageReq: MessageRequest, queueName: String) {
            val message = StringGenerator
                .withSizeInMegaBytes(messageReq.messageSize)
                .toByteArray(StandardCharsets.UTF_8)

            for (i in 1..messageReq.times) {
                establishConnection(brokerUrl, queueName)

                channel?.basicPublish(
                    "",
                    queueName,
                    null,
                    message
                )

                println("Message [$i] sent")
            }
        }
    }
}

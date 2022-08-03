package br.usp.service

import br.usp.models.MessageRequest
import br.usp.utils.StringGenerator
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import io.ktor.server.application.*
import java.nio.charset.StandardCharsets
import kotlin.text.toByteArray

class Producer {

    companion object {

        private var connection: Connection? = null

        private var channel: Channel? = null

        private var amqpHost: String? = null

        private var amqpPort: Int = 5672

        private var amqpUser: String? = null

        private var amqpPass: String? = null

        private var amqpQueue: String? = null

        fun setAmqpProperties(env: ApplicationEnvironment) {
            amqpHost = env
                .config
                .property("amqp.host")
                .getString()

            amqpPort = env
                .config
                .property("amqp.port")
                .getString().toInt()

            amqpUser = env
                .config
                .property("amqp.user")
                .getString()

            amqpPass = env
                .config
                .property("amqp.pass")
                .getString()

            amqpQueue = env
                .config
                .property("amqp.queue")
                .getString()
        }

        private fun createConnection() {
            if (connection == null || !connection!!.isOpen) {
                val factory = ConnectionFactory()

                factory.username = amqpUser
                factory.password = amqpPass
                factory.host = amqpHost
                factory.port = amqpPort

                connection = factory.newConnection()
            }
        }

        private fun establishConnection() {
            if (channel == null || !channel!!.isOpen) {
                createConnection()
                channel = connection?.createChannel()
                channel?.queueDeclare(amqpQueue, false, false, false, null)
            }
        }

        fun publish(messageReq: MessageRequest) {
            val message = StringGenerator
                .withSizeInMegaBytes(messageReq.messageSize)
                .toByteArray(StandardCharsets.UTF_8)

            for (i in 1..messageReq.times) {
                establishConnection()

                channel?.basicPublish(
                    "",
                    amqpQueue,
                    null,
                    message
                )

                println("Message [$i] sent")
            }
        }
    }
}

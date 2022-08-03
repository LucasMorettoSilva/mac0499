import com.rabbitmq.client.*
import java.nio.charset.StandardCharsets

class Consumer {

    companion object {

        private fun createConnection(): Connection {
            val factory = ConnectionFactory()

            factory.username = AmqpConfig.getUser()
            factory.password = AmqpConfig.getPass()
            factory.host = AmqpConfig.getHost()
            factory.port = AmqpConfig.getPort()

            return factory.newConnection()
        }

        fun create() {
            val connection = createConnection()
            val channel = connection.createChannel()
            val queueName = AmqpConfig.getQueueName()

            val consumerTag = "SimpleConsumer"

            channel.queueDeclare(queueName, false, false, false, null)

            println("[$consumerTag] Waiting for messages...")

            val deliverCallback = DeliverCallback {
                    consumerTag: String?,
                    delivery: Delivery ->
                val message = String(delivery.body, StandardCharsets.UTF_8)
                println("[$consumerTag] Received message of size: '${message.length}'")
            }

            val cancelCallback = CancelCallback { consumerTag: String? ->
                println("[$consumerTag] was canceled")
            }

            channel.basicConsume(queueName, true, consumerTag, deliverCallback, cancelCallback)
        }
    }
}

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import java.nio.charset.StandardCharsets

class Consumer {

    companion object {

        fun create(brokerHost: String, queueName: String) {
            val factory = ConnectionFactory()
            val connection = factory.newConnection(brokerHost)
            val channel = connection.createChannel()

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
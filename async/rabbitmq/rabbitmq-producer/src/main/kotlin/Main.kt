import com.rabbitmq.client.ConnectionFactory
import java.nio.charset.StandardCharsets

fun getQueueName(args: Array<String>): String {
    return if (args.isEmpty()) {
        "default_queue"
    } else {
        args[0]
    }
}

fun getMessageSize(args: Array<String>): Double {
    return if (args.isEmpty() || args.size < 2) {
        0.01
    } else {
        args[1].toDouble()
    }
}

fun main(args: Array<String>) {
    val queueName = getQueueName(args)
    val messageSize = getMessageSize(args)
    val message = StringGenerator.withSizeInMegaBytes(messageSize)

    val factory = ConnectionFactory()

    factory.newConnection("amqp://guest:guest@localhost:5672/").use { connection ->
        connection.createChannel().use { channel ->
            channel.queueDeclare(queueName, false, false, false, null)

            channel.basicPublish(
                "",
                queueName,
                null,
                message.toByteArray(StandardCharsets.UTF_8)
            )
            println("[x] Message sent")
        }
    }
}

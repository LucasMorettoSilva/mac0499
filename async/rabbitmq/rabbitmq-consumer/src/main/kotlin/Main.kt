fun getQueueName(args: Array<String>): String {
    return if (args.isEmpty()) {
        "default_queue"
    } else {
        args[0]
    }
}

fun getBrokerHost(): String {
    return System.getenv("BROKER_HOST") ?: return "amqp://guest:guest@localhost:5672/"
}

fun main(args: Array<String>) {
    val queueName = getQueueName(args)
    val brokerHost = getBrokerHost()

    println("broker =  $brokerHost")

    Consumer.create(brokerHost, queueName)
}

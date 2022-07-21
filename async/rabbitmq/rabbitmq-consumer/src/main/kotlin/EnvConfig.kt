class EnvConfig {

    companion object {
        fun getQueueName(): String {
            return System.getenv("QUEUE_NAME") ?: return "default_queue"
        }

        fun getBrokerHost(): String {
            return System.getenv("BROKER_HOST") ?: return "amqp://guest:guest@localhost:5672/"
        }
    }
}

class AmqpConfig {

    companion object {
        fun getQueueName(): String {
            return System.getenv("QUEUE_NAME") ?: return "default_queue"
        }

        fun getHost(): String {
            return System.getenv("BROKER_HOST") ?: return "localhost"
        }

        fun getUser(): String {
            return System.getenv("BROKER_USER") ?: return "guest"
        }

        fun getPass(): String {
            return System.getenv("BROKER_PASS") ?: return "guest"
        }

        fun getPort(): Int {
            return System.getenv("BROKER_PORT").toIntOrNull() ?: return 5672
        }
    }
}

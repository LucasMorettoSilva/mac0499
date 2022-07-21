fun main() {
    val queueName = EnvConfig.getQueueName()
    val brokerHost = EnvConfig.getBrokerHost()

    println("broker =  $brokerHost")

    Consumer.create(brokerHost, queueName)
}

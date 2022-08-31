package br.usp.asyncmsrequest.config.props

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AmqpProps {

    @Value("\${amqp.queue.name}")
    lateinit var queueName: String

    @Value("\${spring.rabbitmq.host}")
    lateinit var host: String

    @Value("\${spring.rabbitmq.port}")
    lateinit var port: String

    @Value("\${spring.rabbitmq.username}")
    lateinit var username: String

    @Value("\${spring.rabbitmq.password}")
    lateinit var password: String
}

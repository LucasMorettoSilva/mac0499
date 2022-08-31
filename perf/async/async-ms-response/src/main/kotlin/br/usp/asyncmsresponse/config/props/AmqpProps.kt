package br.usp.asyncmsresponse.config.props

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AmqpProps {

    @Value("\${amqp.queue.name}")
    lateinit var queueName: String
}

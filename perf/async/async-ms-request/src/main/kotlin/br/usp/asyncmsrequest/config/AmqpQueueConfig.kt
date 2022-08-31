package br.usp.asyncmsrequest.config

import br.usp.asyncmsrequest.config.props.AmqpProps
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmqpQueueConfig(
    private val amqpProps: AmqpProps
) {

    @Bean
    fun queue(): Queue = Queue(amqpProps.queueName, true)
}

package br.usp.asyncmsconnection.config.amqp

import br.usp.asyncmsconnection.props.AmqpNewFriendshipProps
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmqpNewFriendshipQueueConfig(
    private val amqpNewFriendshipProps: AmqpNewFriendshipProps
) {

    @Bean
    fun queue(): Queue = Queue(amqpNewFriendshipProps.queueName, true)
}

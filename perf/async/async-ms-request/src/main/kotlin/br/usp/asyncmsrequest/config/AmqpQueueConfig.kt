package br.usp.asyncmsrequest.config

import br.usp.asyncmsrequest.config.props.AmqpProps
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmqpQueueConfig(
    private val amqpProps: AmqpProps
) {

    @Bean
    fun queue(): Queue = Queue(amqpProps.queueName, true)

//    @Bean
//    fun connectionFactory(): ConnectionFactory? {
//        val connectionFactory = CachingConnectionFactory(
//            amqpProps.host,
//            amqpProps.port.toInt()
//        )
//
//        connectionFactory.username = amqpProps.username
//        connectionFactory.setPassword(amqpProps.password)
//        connectionFactory.isPublisherReturns = true
//
//        return connectionFactory
//    }
}

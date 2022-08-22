package br.usp.asyncmsconnection.props

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AmqpNewFriendshipProps {

    @Value("\${amqp.new-friendship.queue.name}")
    lateinit var queueName: String
}

package br.usp.asyncmsconnection.service.friendship

import br.usp.asyncmsconnection.model.entity.Friendship
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class EstablishFriendshipAmqpProducerImpl(
    private val queue: Queue,
    private val rabbitTemplate: RabbitTemplate
): EstablishFriendshipAmqpProducer {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun publish(friendship: Friendship) {
        log.info("publish() : publishing event : {}", friendship)

        rabbitTemplate.convertAndSend(
            queue.name,
            friendship
        )
    }
}

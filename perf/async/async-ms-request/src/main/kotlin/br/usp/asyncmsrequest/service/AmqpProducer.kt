package br.usp.asyncmsrequest.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class AmqpProducer(
    private val queue: Queue,
    private val rabbitTemplate: RabbitTemplate
) {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun publish(msg: String) {
        log.info("publish() : publishing message")

        rabbitTemplate.convertAndSend(
            queue.name,
            msg
        )
    }
}

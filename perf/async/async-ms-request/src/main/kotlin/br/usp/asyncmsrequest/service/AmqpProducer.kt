package br.usp.asyncmsrequest.service

import br.usp.asyncmsrequest.config.AmqpMessagePostProcessor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
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

        rabbitTemplate.setBeforePublishPostProcessors(
            AmqpMessagePostProcessor()
        )

        val res = rabbitTemplate.convertSendAndReceive(
            queue.name,
            msg
        )

        val resReceiveTime = System.currentTimeMillis()

        log.info("$res-$resReceiveTime")
    }
}

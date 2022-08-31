package br.usp.asyncmsresponse.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class AmqpConsumer {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @RabbitListener(queues = ["default"])
    fun consume(msg: String) {
        log.info("consume() : received new message")
    }
}

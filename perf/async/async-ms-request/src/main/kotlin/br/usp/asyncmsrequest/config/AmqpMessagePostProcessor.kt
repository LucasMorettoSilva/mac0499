package br.usp.asyncmsrequest.config

import br.usp.asyncmsrequest.enums.CustomHeaders
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessagePostProcessor

class AmqpMessagePostProcessor: MessagePostProcessor {

    override fun postProcessMessage(message: Message): Message {
        message.messageProperties.setHeader(
            CustomHeaders.REQ_SEND_TIME.value,
            System.currentTimeMillis()
        )

        return message
    }
}

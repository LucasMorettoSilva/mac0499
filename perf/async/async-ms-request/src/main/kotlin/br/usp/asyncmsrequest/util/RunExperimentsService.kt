package br.usp.asyncmsrequest.util

import br.usp.asyncmsrequest.service.AmqpProducer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RunExperimentsService(
    private val messageReader: MessageReader,
    private val amqpProducer: AmqpProducer
) {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun run(repetitions: Int, msgSize: String) {
        val msg = messageReader.loadMessage(msgSize)

        for (i in 1..repetitions) {
            log.info("run() : repetition $i of $repetitions: $msgSize MB")

            val response = amqpProducer.publish(msg)

            PerfTrace.log(msgSize, response)
        }

        log.info("run() : finished experiments")
    }
}

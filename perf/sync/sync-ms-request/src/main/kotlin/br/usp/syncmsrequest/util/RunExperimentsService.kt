package br.usp.syncmsrequest.util

import br.usp.syncmsrequest.service.SyncMsResponseService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RunExperimentsService(
    private val messageReader: MessageReader,
    private val syncMsResponseService: SyncMsResponseService
) {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun run(repetitions: Int, msgSize: String) {
        val msg = messageReader.loadMessage(msgSize)

        for (i in 1..repetitions) {
            log.info("run() : repetition $i of $repetitions: $msgSize MB")

            val response = syncMsResponseService.callPing(msg)

            PerfTrace.log(msgSize, response.headers)
        }

        log.info("run() : finished experiments")
    }
}

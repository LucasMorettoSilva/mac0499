package br.usp.asyncmsrequest.controller

import br.usp.asyncmsrequest.util.MessageWriter
import br.usp.asyncmsrequest.util.RunExperimentsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/run")
class RunExperimentsController(
    private val runExperimentsService: RunExperimentsService,
    private val messageWriter: MessageWriter
) {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("{msgSize}/{repetitions}")
    @ResponseStatus(HttpStatus.OK)
    fun run(
        @PathVariable msgSize: String,
        @PathVariable repetitions: Int) {
        log.info("run() : msgSize : {} : repetitions : {}", msgSize, repetitions)

        runExperimentsService.run(repetitions, msgSize)
    }

    @GetMapping("{msgSize}")
    fun gen(@PathVariable msgSize: Double) {
        messageWriter.writeMessage(msgSize)
    }
}

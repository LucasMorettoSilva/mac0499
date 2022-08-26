package br.usp.syncmsresponse.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/ping")
class PingController {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun ping(@RequestBody message: String): ResponseEntity<String> {
        val receiveTime = System.currentTimeMillis()

        log.info("ping() : received message with {}", message.length * 4)

        val headers = HttpHeaders()

        headers.set(
            "req-receive-time-millis",
            receiveTime.toString()
        )

        return ResponseEntity.ok().headers(headers).build()
    }
}

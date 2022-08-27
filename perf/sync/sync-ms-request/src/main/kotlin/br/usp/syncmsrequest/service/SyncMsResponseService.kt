package br.usp.syncmsrequest.service

import br.usp.syncmsrequest.config.props.SyncMsResponseProps
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.net.URI

@Service
class SyncMsResponseService(
    private val restTemplate: RestTemplate
) {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    lateinit var syncMsResponseProps: SyncMsResponseProps

    fun callPing(msg: String): ResponseEntity<String> {
        log.info("callPing() : calling ping endpoint")

        try {
            val response = restTemplate.postForEntity(
                URI(syncMsResponseProps.host + syncMsResponseProps.endpointPing),
                msg,
                String::class.java
            )

            log.info("callPing() : response status : {}", response.statusCode)

            return response
        } catch (e: Exception) {
            log.error("callPing() : failed", e)

            throw e
        }
    }
}

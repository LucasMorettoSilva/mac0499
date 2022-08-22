package br.usp.syncmsconnection.service.friendship

import br.usp.syncmsconnection.model.entity.Friendship
import br.usp.syncmsconnection.props.SyncMsConnectionProps
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.net.URI

@Service
class FriendshipServiceImpl(
    private val restTemplate: RestTemplate): FriendshipService {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    lateinit var syncMsConnectionProps: SyncMsConnectionProps

    override fun exists(userEmail1: String, userEmail2: String): Boolean {
        try {
            val uri = URI(
                syncMsConnectionProps.host +
                syncMsConnectionProps.endpointGetFriendship
                    .replace("{email1}", userEmail1)
                    .replace("{email2}", userEmail2)
            )

            log.info("exists() : calling sync-ms-connection to check friendship")

            restTemplate.getForObject<Friendship>(uri)

            log.info("exists() : friendship exists")

            return true
        } catch (exception: HttpStatusCodeException) {
            if (exception.statusCode == HttpStatus.NOT_FOUND) {
                log.info("exists() : friendship does not exists")

                return false
            }

            log.error("exists() : failed", exception)

            throw exception
        }
    }
}

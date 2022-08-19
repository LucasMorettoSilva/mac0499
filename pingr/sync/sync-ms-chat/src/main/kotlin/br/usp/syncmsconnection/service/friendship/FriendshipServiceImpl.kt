package br.usp.syncmsconnection.service.friendship

import br.usp.syncmsconnection.model.entity.Friendship
import br.usp.syncmsconnection.props.SyncMsConnectionProps
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

            restTemplate.getForObject<Friendship>(uri)

            return true
        } catch (exception: HttpStatusCodeException) {
            if (exception.statusCode == HttpStatus.NOT_FOUND) {
                return false
            }

            throw exception
        }
    }
}

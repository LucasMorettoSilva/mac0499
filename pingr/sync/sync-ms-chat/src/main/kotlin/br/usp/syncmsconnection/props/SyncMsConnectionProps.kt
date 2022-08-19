package br.usp.syncmsconnection.props

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SyncMsConnectionProps {

    @Value("\${api.host.sync-ms-connection}")
    lateinit var host: String

    @Value("\${api.endpoint.get-friendship}")
    lateinit var endpointGetFriendship: String
}

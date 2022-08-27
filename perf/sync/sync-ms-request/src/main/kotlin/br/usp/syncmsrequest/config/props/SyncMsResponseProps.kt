package br.usp.syncmsrequest.config.props

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SyncMsResponseProps {

    @Value("\${api.host.sync-ms-response}")
    lateinit var host: String

    @Value("\${api.endpoint.ping}")
    lateinit var endpointPing: String
}

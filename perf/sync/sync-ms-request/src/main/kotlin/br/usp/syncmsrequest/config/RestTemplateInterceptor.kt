package br.usp.syncmsrequest.config

import br.usp.syncmsrequest.enums.CustomHeaders
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import java.io.IOException

class RestTemplateInterceptor : ClientHttpRequestInterceptor {

    @Throws(IOException::class)
    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {
        val sendRequestTime = System.currentTimeMillis()

        val response: ClientHttpResponse = execution.execute(request, body)

        response.headers.add(
            CustomHeaders.REQ_SEND_TIME.value,
            sendRequestTime.toString()
        )

        val receiveResponseTime = System.currentTimeMillis()

        response.headers.add(
            CustomHeaders.RES_RECEIVE_TIME.value,
            receiveResponseTime.toString()
        )

        return response
    }
}

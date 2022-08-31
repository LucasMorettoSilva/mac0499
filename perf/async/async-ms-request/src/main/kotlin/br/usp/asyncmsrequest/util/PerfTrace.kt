package br.usp.asyncmsrequest.util

import br.usp.asyncmsrequest.enums.CustomHeaders
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders

class PerfTrace {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)

        fun log(msgSize:String, headers: HttpHeaders) {
            val reqSendTime = getHeaderAsLong(headers, CustomHeaders.REQ_SEND_TIME)
            val reqReceiveTime = getHeaderAsLong(headers, CustomHeaders.REQ_RECEIVE_TIME)
            val resSendTime = getHeaderAsLong(headers, CustomHeaders.RES_SEND_TIME)
            val resReceiveTime = getHeaderAsLong(headers, CustomHeaders.RES_RECEIVE_TIME)
            val totalTime = resReceiveTime - reqSendTime
            val reqTotalTime = reqReceiveTime - reqSendTime
            val resTotalTime = resReceiveTime - resSendTime

            log.trace("$msgSize,$totalTime,$reqSendTime,$reqReceiveTime,$reqTotalTime,$resSendTime,$resReceiveTime,$resTotalTime")
        }

        private fun getHeaderAsLong(
            headers: HttpHeaders,
            headerName: CustomHeaders): Long =
            headers.getValue(headerName.value).first().toLong()
    }
}

package br.usp.asyncmsrequest.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.stream.Collectors

class PerfTrace {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)

        fun log(msgSize:String, response: String) {
            val metrics = getHeaderAsLongList(response)

            val reqSendTime = metrics[0]
            val reqReceiveTime = metrics[1]
            val resSendTime = metrics[2]
            val resReceiveTime = metrics[3]
            val totalTime = resReceiveTime - reqSendTime
            val reqTotalTime = reqReceiveTime - reqSendTime
            val resTotalTime = resReceiveTime - resSendTime

            log.trace("$msgSize,$totalTime,$reqSendTime,$reqReceiveTime,$reqTotalTime,$resSendTime,$resReceiveTime,$resTotalTime")
        }

        private fun getHeaderAsLongList(response: String): List<Long> =
            response.split("-").stream().map { it.toLong() }.collect(Collectors.toList())
    }
}

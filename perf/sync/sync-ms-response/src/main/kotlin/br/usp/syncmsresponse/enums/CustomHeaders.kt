package br.usp.syncmsresponse.enums

enum class CustomHeaders(val value: String) {
    REQ_SEND_TIME("req-send-time"),
    REQ_RECEIVE_TIME("req-receive-time"),
    RES_SEND_TIME("res-send-time"),
    RES_RECEIVE_TIME("res-receive-time")
}

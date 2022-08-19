package br.usp.syncmsconnection.service.friendship

import br.usp.syncmsconnection.model.request.MessageRequest

interface SendMessageService {

    fun sendMessage(messageReq: MessageRequest)
}

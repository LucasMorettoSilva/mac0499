package br.usp.syncmsconnection.service.message

import br.usp.syncmsconnection.model.request.MessageRequest

interface SendMessageService {

    fun sendMessage(messageReq: MessageRequest)
}

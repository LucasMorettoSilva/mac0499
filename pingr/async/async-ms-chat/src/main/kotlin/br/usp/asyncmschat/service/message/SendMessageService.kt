package br.usp.asyncmschat.service.message

import br.usp.asyncmschat.model.entity.Message
import br.usp.asyncmschat.model.request.MessageRequest

interface SendMessageService {

    fun sendMessage(messageReq: MessageRequest): Message
}

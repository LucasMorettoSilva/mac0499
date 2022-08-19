package br.usp.syncmsconnection.service.message

import br.usp.syncmsconnection.model.entity.Message

interface MessageService {

    fun save(message: Message): Message

    fun findByChatId(chatId: String): List<Message>
}

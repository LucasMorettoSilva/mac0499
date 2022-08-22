package br.usp.asyncmschat.service.message

import br.usp.asyncmschat.model.entity.Message

interface MessageService {

    fun save(message: Message): Message

    fun findByChatId(chatId: String): List<Message>
}

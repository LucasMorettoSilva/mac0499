package br.usp.syncmsconnection.service.message

import br.usp.syncmsconnection.model.entity.Message
import br.usp.syncmsconnection.repository.MessageRepository
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
    private val messageRepository: MessageRepository
): MessageService {

    override fun save(message: Message): Message =
        messageRepository.save(message)

    override fun findByChatId(chatId: String): List<Message> =
        messageRepository.findByChatIdOrderByCreationDate(chatId)
}

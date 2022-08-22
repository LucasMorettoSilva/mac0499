package br.usp.syncmsconnection.service.message

import br.usp.syncmsconnection.model.entity.Message
import br.usp.syncmsconnection.repository.MessageRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
    private val messageRepository: MessageRepository
): MessageService {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun save(message: Message): Message {
        log.info("save() : inserting message to db : {}", message)

        val entity = messageRepository.save(message)

        log.info("save() : message successfully inserted : {}", message)

        return entity
    }

    override fun findByChatId(chatId: String): List<Message> {
        log.info("findByChatId() : searching all messages in chat : {}", chatId)


        val msgs = messageRepository.findByChatIdOrderByCreationDateDesc(chatId)

        log.info("findByChatId() : total of messages found : {}", msgs.size)

        return msgs
    }
}

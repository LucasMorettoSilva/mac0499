package br.usp.syncmsconnection.service.chat

import br.usp.syncmsconnection.model.entity.Chat
import br.usp.syncmsconnection.model.response.FullChat
import br.usp.syncmsconnection.repository.ChatRepository
import br.usp.syncmsconnection.service.message.MessageService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ChatServiceImpl(
    private val chatUsersService: ChatUsersService,
    private val messageService: MessageService,
    private val chatRepository: ChatRepository
): ChatService {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun findAll(): List<Chat> {
        log.info("findAll() : searching for all chats in db")

        val chats = chatRepository.findAll()

        log.info("findAll() : total chats found : {}", chats.size)

        return chats
    }

    override fun createNewChat(): Chat {
        log.info("createNewChat() : creating new chat")

        val chat = save(Chat())

        log.info("createNewChat() : chat successfully created : {}", chat)

        return chat
    }

    override fun save(chat: Chat): Chat {
        log.info("save() : updating chat : {}", chat)

        val entity = chatRepository.save(chat)

        log.info("save() : chat successfully updated : {}", entity)

        return entity
    }

    override fun findById(id: String): Chat? {
        log.info("findById() : searching for chat with id : {}", id)

        val chat = chatRepository.findByIdOrNull(id)

        log.info("findById() : chat found : {}", chat)

        return chat
    }

    override fun findFullChatById(id: String): FullChat {
        val chat = findById(id)
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "chat with id $id not found"
            )

        log.info("findFullChatById() : searching for full chat with id : {}", id)

        return FullChat(
            chat,
            chatUsersService.findAllUsersFromChat(id),
            messageService.findByChatId(id)
        )
    }

    override fun findFullChatBetweenUsers(
        userEmail1: String,
        userEmail2: String): FullChat {
        val commonChatId = chatUsersService.findCommonChatId(
            userEmail1,
            userEmail2
        ) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "chat not found"
        )

        return findFullChatById(commonChatId)
    }
}

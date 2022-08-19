package br.usp.syncmsconnection.service.chat

import br.usp.syncmsconnection.model.entity.Chat
import br.usp.syncmsconnection.model.response.FullChat
import br.usp.syncmsconnection.repository.ChatRepository
import br.usp.syncmsconnection.service.message.MessageService
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

    override fun createNewChat(): Chat = save(Chat())

    override fun save(chat: Chat): Chat =
        chatRepository.save(chat)

    override fun findById(id: String): Chat? =
        chatRepository.findByIdOrNull(id)

    override fun findFullChatById(id: String): FullChat {
        val chat = chatRepository.findByIdOrNull(id)
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "chat with id $id not found"
            )

        return FullChat(
            chat,
            chatUsersService.findAllUsersFromChat(id),
            messageService.findByChatId(id)
        )
    }
}

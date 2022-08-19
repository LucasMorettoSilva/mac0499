package br.usp.syncmsconnection.service.chat

import br.usp.syncmsconnection.model.entity.Chat
import br.usp.syncmsconnection.model.entity.ChatUsers
import br.usp.syncmsconnection.repository.ChatUsersRepository
import org.springframework.stereotype.Service

@Service
class ChatUsersServiceImpl(
    private val chatUsersRepository: ChatUsersRepository
): ChatUsersService {

    override fun save(chatUsers: ChatUsers): ChatUsers =
        chatUsersRepository.save(chatUsers)

    override fun findCommonChatId(userEmail1: String, userEmail2: String): String? =
        chatUsersRepository.findCommonChatId(userEmail1, userEmail2)

    override fun addUserToChat(userEmail: String, chat: Chat): ChatUsers =
        chatUsersRepository.save(ChatUsers(chat.id, userEmail))

    override fun findAllUsersFromChat(chatId: String): List<String> =
        chatUsersRepository.findAllUsersFromChat(chatId)
}

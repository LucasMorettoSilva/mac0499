package br.usp.syncmsconnection.service.chat

import br.usp.syncmsconnection.model.entity.Chat
import br.usp.syncmsconnection.model.entity.ChatUsers

interface ChatUsersService {

    fun save(chatUsers: ChatUsers): ChatUsers

    fun findCommonChatId(userEmail1: String, userEmail2: String): String?

    fun addUserToChat(userEmail: String, chat: Chat): ChatUsers

    fun findAllUsersFromChat(chatId: String): List<String>
}
